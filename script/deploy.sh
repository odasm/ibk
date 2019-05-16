#!/bin/bash
# 添加这句以防子进程被杀掉
export JENKINS_NODE_COOKIE=dontkillme
export BUILD_ID=dontkillme

mvn jar:jar install:install help:evaluate -Dexpression=project.name

NAME=`mvn help:evaluate -Dexpression=project.name | grep "^[^\[]"`
if [[ -n "$NAME" ]];then
    echo "name:$NAME"
else
    echo "name not found"
    exit 1
fi

VERSION=`mvn help:evaluate -Dexpression=project.version | grep "^[^\[]"`
if [[ -n "$VERSION" ]];then
    echo "version:$VERSION"
else
    echo "version not found"
    exit 2
fi

echo "copy $NAME"
if [[ ! -d "/usr/local/$NAME" ]];then
    mkdir /usr/local/${NAME}
fi
cp -f target/${NAME}-${VERSION}.jar /usr/local/${NAME}/ibk.jar
cp -f script/start.sh /usr/local/${NAME}/start.sh
cp -f script/stop.sh /usr/local/${NAME}/stop.sh
chmod +x /usr/local/${NAME}/*.sh

echo "stop Application"
/usr/local/${NAME}/stop.sh
echo "start Application"
/usr/local/${NAME}/start.sh