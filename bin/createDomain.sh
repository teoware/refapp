#!/bin/bash

echo "INFO: Creating domain..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

bash -c "${ASADMIN_CMD} create-domain --savemasterpassword true ${DOMAIN}"
test $? -ne 0 && echo "ERROR: Failed to create domain '${DOMAIN}'" && exit 1

echo "INFO: Domain '${DOMAIN}' created successfully" && exit 0
