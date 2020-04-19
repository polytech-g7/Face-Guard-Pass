#!/bin/sh

cd ..

LIQUIBASE_DIR_PATH=../path_for_schema/schema.xml

ls

if [ -n "$1" ]; then
  LIQUIBASE_DIR_PATH=$1
fi

java -jar ./target/name.jar --changeLogFile="$LIQUIBASE_DIR_PATH" update;