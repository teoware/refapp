#!/bin/bash

FILE_PATH="$(dirname "$(readlink -f ${BASH_SOURCE[0]})")"

. ${FILE_PATH}/env.sh

bash -c "${ASADMIN_CMD} add-resources ${JDBC_XML_FILE}"
