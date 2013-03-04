#!/bin/bash

echo "INFO: Starting full execution..."

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

. ${FILE_PATH}/env.sh
test $? -ne 0 && echo "ERROR: Failed to load env variables" && exit 1

if [ -d ${DOMAIN_FOLDER} ]; then
	bash -c "${FILE_PATH}/startDomain.sh"
	test $? -ne 0 && echo "ERROR: Execution failed at step '1 (startDomain)'" && exit 1
	
	bash -c "${FILE_PATH}/deleteJDBCResource.sh"
	test $? -ne 0 && echo "ERROR: Execution failed at step '2 (deleteJDBCResource)'" && exit 1
	
	bash -c "${FILE_PATH}/stopDomain.sh"
	test $? -ne 0 && echo "ERROR: Execution failed at step '3 (stopDomain)'" && exit 1
	
	bash -c "${FILE_PATH}/deleteDomain.sh"
	test $? -ne 0 && echo "ERROR: Execution failed at step '4 (deleteDomain)'" && exit 1
fi

#bash -c "${FILE_PATH}/deleteDatabase.sh"
#test $? -ne 0 && echo "ERROR: Execution failed at step '5 (deleteDatabase)'" && exit 1

bash -c "${FILE_PATH}/createDomain.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '6 (createDomain)'" && exit 1

bash -c "${FILE_PATH}/startDomain.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '7 (startDomain)'" && exit 1

bash -c "${FILE_PATH}/createJDBCResource.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '8 (createJDBCResource)'" && exit 1

bash -c "${FILE_PATH}/createDatabaseSchema.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '9 (createDatabaseSchema)'" && exit 1

bash -c "${FILE_PATH}/buildApplication.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '10 (buildApplication)'" && exit 1

bash -c "${FILE_PATH}/deployApplication.sh"
test $? -ne 0 && echo "ERROR: Execution failed at step '11 (deployApplication)'" && exit 1

echo "INFO: Execution finished successfully!" && exit 0
