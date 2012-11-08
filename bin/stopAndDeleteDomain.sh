#!/bin/bash

FILE_PATH="$(dirname "$(readlink -f ${BASH_SOURCE[0]})")"

${FILE_PATH}/stopDomain.sh

${FILE_PATH}/deleteDomain.sh
