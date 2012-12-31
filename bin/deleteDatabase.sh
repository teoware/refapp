#!/bin/bash

echo "INFO: Deleting database folder..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

bash -c "rd -rf ${DATABASE_FOLDER}" > ${ERROR_LOG} 2>&1
test $? -ne 0 && echo "ERROR: Failed to delete database folder" && exit 1

echo "INFO: Database folder deleted successfully" && exit 0
