#!/bin/bash

FILE_PATH="$(dirname "$(readlink -f ${BASH_SOURCE[0]})")"

. ${FILE_PATH}/env.sh

bash -c "${ASADMIN_CMD} delete-jdbc-connection-pool --cascade true ${CONNECTION_POOL_NAME}"
