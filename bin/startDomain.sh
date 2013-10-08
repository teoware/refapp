#!/bin/bash

echo "INFO: Starting domain..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

bash -c "${ASADMIN_CMD} start-database" > ${ERROR_LOG} 2>&1
test $? -ne 0 && echo "ERROR: Failed to start database" && exit 1

bash -c "${ASADMIN_CMD} start-domain ${DOMAIN}" > ${ERROR_LOG} 2>&1
if [ $? -eq 1 ]
then
	cat ${ERROR_LOG} | grep "There is a process already using the admin port 4848" > /dev/null 2>&1
fi
test $? -ne 0 && echo "ERROR: Failed to start domain '${DOMAIN}'" && exit 1

echo "INFO: Domain '${DOMAIN}' started successfully" && exit 0
