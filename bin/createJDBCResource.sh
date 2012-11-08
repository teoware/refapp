#!/bin/bash

FILE_PATH="$(dirname "$(readlink -f ${BASH_SOURCE[0]})")"

. ${FILE_PATH}/env.sh

${ASADMIN_CMD} add-resources ${XML_FILE}
