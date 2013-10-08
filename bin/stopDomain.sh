#!/bin/bash

echo "INFO: Stopping domain..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

. ${FILE_PATH}/env.sh

bash -c "${ASADMIN_CMD} stop-database" > ${ERROR_LOG} 2>&1
test $? -ne 0 && echo "ERROR: Failed to stop database" && exit 1

bash -c "${ASADMIN_CMD} stop-domain ${DOMAIN}" > ${ERROR_LOG} 2>&1
test $? -ne 0 && echo "ERROR: Failed to stop domain '${DOMAIN}'" && exit 1

echo "INFO: Domain '${DOMAIN}' stopped successfully" && exit 0
