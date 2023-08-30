#!/bin/bash

BIN_DIR=$(dirname $0)
 PROJECT_HOME=${PROJECT_HOME:-$(
    cd $BIN_DIR/..
    pwd
  )}

  JAVA_OPTS=${JAVA_OPTS:-"-server -Duser.timezone=${SPRING_JACKSON_TIME_ZONE}
   -Xms1g
   -Xmx1g
   -Xmn512m
   -XX:+UseG1GC
   -XX:+PrintGCDetails
   -Xloggc:gc.log
   -XX:+HeapDumpOnOutOfMemoryError
   -XX:HeapDumpPath=dump.hprof"}
  echo "$JAVA_HOME/bin/java $JAVA_OPTS \
            -cp "$PROJECT_HOME/conf":"$PROJECT_HOME/libs/*" \
            com.zlht.pbr.algorithm.management.AlgorithmManagementApi"

  nohup $JAVA_HOME/bin/java $JAVA_OPTS \
    -cp "$PROJECT_HOME/conf":"$PROJECT_HOME/libs/*" \
    com.zlht.pbr.algorithm.management.AlgorithmManagementApi >/dev/null 2>&1 &

  if [ $? -eq 0 ]; then
    echo "Application started successfully."
  else
    echo "Application failed to start."
  fi
