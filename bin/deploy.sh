#!/bin/bash

FILE_PATH="$(dirname "$(readlink -f ${BASH_SOURCE[0]})")"

${FILE_PATH}/env.sh

${ASADMIN_CMD} deploy --contextroot ${CONTEXT_ROOT} --name ${APP_NAME} ${EAR_FILE}
