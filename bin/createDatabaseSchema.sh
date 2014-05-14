#!/bin/bash

echo "INFO: Creating database schema objects..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

POM_FILE=${FILE_PATH}/../etc/database/pom.xml
test ! -f ${POM_FILE} && echo "ERROR: Failed to find Maven build file" && exit 1

bash -c "${MAVEN_CMD} -q -f ${POM_FILE} clean install" > ${ERROR_LOG} 2>&1
test $? -ne 0 && echo "ERROR: Failed to create database schema objects using Maven" && exit 1

echo "INFO: Database schema objects created successfully" && exit 0
