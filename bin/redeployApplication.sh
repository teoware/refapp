#!/bin/bash

echo "INFO: Redeploying application..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

test ! -f ${DEPLOY_FILE} && echo "ERROR: Failed to find application deploy file" && exit 1

bash -c "${ASADMIN_CMD} redeploy --name ${APP_NAME} ${DEPLOY_FILE}"
test $? -ne 0 && echo "ERROR: Failed to redeploy application" && exit 1

echo "INFO: Application redeployed successfully" && exit 0
