#!/bin/bash

echo "INFO: Deploying application..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

test ! -f ${DEPLOY_FILE} && echo "ERROR: Failed to find application deploy file" && exit 1

bash -c "${ASADMIN_CMD} deploy --contextroot ${CONTEXT_ROOT} --name ${APP_NAME} ${DEPLOY_FILE}" > ${ERROR_LOG} 2>&1
test $? -ne 0 && echo "ERROR: Failed to deploy application" && exit 1

echo "INFO: Application deployed successfully" && exit 0
