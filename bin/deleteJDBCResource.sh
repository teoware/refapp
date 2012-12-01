#!/bin/bash

echo "INFO: Deleting JDBC resources..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

bash -c "${ASADMIN_CMD} delete-jdbc-connection-pool --cascade true ${CONNECTION_POOL_NAME}" > ${ERROR_LOG} 2>&1
if [ $? -eq 1 ]
then
	cat ${ERROR_LOG} | grep "${CONNECTION_POOL_NAME} does not exist" > /dev/null 2>&1
fi
test $? -ne 0 && echo "ERROR: Failed to delete JDBC resources" && exit 1

echo "INFO: JDBC resources deleted successfully" && exit 0
