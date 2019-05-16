#!/bin/bash
log_dir="/var/log/ibk"
log_file="ibk.log"
exe_file="/usr/local/ibk/ibk.jar"
pid=`ps -ef | grep ${exe_file} | grep -v grep | awk '{print $2}'`
echo "pid：$pid"
if [[ -n "$pid" ]];then
    kill -9 ${pid}
    echo "$(date "+%Y-%m-%d %H:%M:%S")  stop Application" >> "$log_dir/$log_file"
fi