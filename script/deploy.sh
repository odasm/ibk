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
cp target/${NAME}-${VERSION}.jar /usr/local/${NAME}.jar
chmod +x /usr/local/${NAME}.jar

if [[ -f "/usr/local/$NAME.jar" ]];then
    echo "stop Application"
    pid=`ps -ef | grep ${NAME} | grep -v grep | awk '{print $2}'`
    echo "pid：$pid"
    if [[ -n "$pid" ]];then
        kill -9 ${pid}
    fi

    echo "start Application"
    echo "$(date "+%Y-%m-%d %H:%M:%S") start Application" >> /var/log/${NAME}.log
    nohup java -jar /usr/local/${NAME}.jar >> /var/log/${NAME}.log &
    ps -ef | grep ${NAME}
    echo 'deploy finish'
else
    echo "${NAME}.jar not found"
    exit 3
fi