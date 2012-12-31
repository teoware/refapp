#!/bin/bash

echo "INFO: Creating JDBC resources..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

bash -c "${ASADMIN_CMD} add-resources ${JDBC_XML_FILE}" > ${ERROR_LOG} 2>&1
test $? -ne 0 && echo "Failed to create JDBC resources" && exit 1

bash -c "${ASADMIN_CMD} ping-connection-pool ${CONNECTION_POOL_NAME}" > ${ERROR_LOG} 2>&1
test $? -ne 0 && echo "Failed to ping JDBC connection pool" && exit 1

echo "INFO: JDBC resources created successfully" && exit 0
