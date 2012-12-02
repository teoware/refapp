#!/bin/bash

echo "INFO: Undeploying application..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

bash -c "${ASADMIN_CMD} undeploy ${APP_NAME}" > ${ERROR_LOG} 2>&1
test $? -ne 0 && echo "ERROR: Failed to undeploy application" && exit 1

echo "INFO: Application undeployed successfully" && exit 0
