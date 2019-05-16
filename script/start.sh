#!/bin/bash
log_dir="/var/log/ibk"
log_file="ibk.log"
exe_file="/usr/local/ibk/ibk.jar"
param="-Ddb.url=$IBK_DB_URL"
param="$param -Ddb.user=$IBK_DB_USER"
param="$param -Ddb.pass=$IBK_DB_PASS"
param="$param -Demail.host=$IBK_EMAIL_HOST"
param="$param -Demail.user=$IBK_EMAIL_USER"
param="$param -Demail.pass=$IBK_EMAIL_PASS"
if [[ ! -d ${log_dir} ]];then
    mkdir ${log_dir}
fi
if [[ -f "$log_dir/$log_file" ]];then
    mv "$log_dir/$log_file" "$log_dir/$(date "+%Y-%m-%d_%H:%M:%S")_$log_file"
fi
echo "$(date "+%Y-%m-%d %H:%M:%S")  start Application" >> "$log_dir/$log_file"
echo "$(date "+%Y-%m-%d %H:%M:%S")  java -jar $param $exe_file >> $log_dir/$log_file &" >> "$log_dir/$log_file"
nohup java -jar ${param} ${exe_file} >> "$log_dir/$log_file" &
echo 'deploy finish'