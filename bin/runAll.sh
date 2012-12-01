#!/bin/bash

echo "INFO: Starting full execution..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

bash -c "${FILE_PATH}/startDomain.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '1'" && exit 1

bash -c "${FILE_PATH}/deleteJDBCResource.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '2'" && exit 1

bash -c "${FILE_PATH}/stopAndDeleteDomain.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '3'" && exit 1

bash -c "${FILE_PATH}/createAndStartDomain.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '4'" && exit 1

bash -c "${FILE_PATH}/createJDBCResource.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '5'" && exit 1

bash -c "${FILE_PATH}/createDatabaseSchema.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '6'" && exit 1

bash -c "${FILE_PATH}/buildAndDeployApplication.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '7'" && exit 1

echo "INFO: Execution finished successfully!" && exit 0
