#!/bin/bash

echo "INFO: Deleting domain..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

bash -c "${ASADMIN_CMD} delete-domain ${DOMAIN}" > ${ERROR_LOG} 2>&1
test $? -ne 0 && echo "ERROR: Failed to delete domain '${DOMAIN}'" && exit 1

echo "INFO: Domain '${DOMAIN}' deleted successfully" && exit 0
