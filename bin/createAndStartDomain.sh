#!/bin/bash

FILE_PATH="$(dirname "$(readlink -f ${BASH_SOURCE[0]})")"

${FILE_PATH}/createDomain.sh

${FILE_PATH}/createJDBCResource.sh

${FILE_PATH}/startDomain.sh
