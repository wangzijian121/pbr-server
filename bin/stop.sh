#!/bin/bash

PID=$(ps -ef | grep 'com.zlht.pbr.algorithm.management.AlgorithmManagementApi' | grep -v grep | awk '{print $2}')

if [ -z "$PID" ]; then
    echo "No AlgorithmManagementApi process found to stop"
    exit 1
fi

echo "Stopping AlgorithmManagementApi process $PID"
kill -SIGTERM $PID

# Wait for up to 30 seconds for the process to stop
for i in {1..30}; do
    if ps -p $PID > /dev/null; then
        sleep 1
    else
        echo "AlgorithmManagementApi process $PID stopped"
        exit 0
    fi
done

echo "AlgorithmManagementApi process $PID did not stop in a timely manner, sending SIGKILL"
kill -SIGKILL $PID
exit 0
