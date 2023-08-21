#!/bin/sh
bin_dir=`dirname $0`
bin_dir=`cd ${bin_dir};pwd`
work_dir=$(dirname $bin_dir)
cd $work_dir

######################main###############
pid=''

function getPid(){
  pid=$(ps -ef | grep BdpMonitorApplication | grep -v grep |head -n 1 | awk '{print $2}')
}

getPid
if [  "$pid" != '' ]; then

        kill -15  $pid
        echo "停止进程>>>$pid"
        getPid
        if [ "$pid" == ''  ]; then
            echo "停止成功！进程号：$pid"
        fi
  else
         echo "未启动bdp-monitor程序！无需停止！"
fi


