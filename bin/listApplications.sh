#!/bin/bash

echo "INFO: Deploying application..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

bash -c "${ASADMIN_CMD} list applications"
test $? -ne 0 && echo "ERROR: Failed to list applications" && exit 1

echo "INFO: Applications listed successfully" && exit 0
