#!/bin/bash

PID=$(ps -ef | grep 'com.zlht.pbr.algorithm.management.AlgorithmManagementApi' | grep -v grep | awk '{print $2}')

if [ -z "$PID" ]; then
    echo "No AlgorithmManagementApi process found to stop"
    exit 1
fi

echo "Stopping AlgorithmManagementApi process $PID"
kill $PID

exit 0
