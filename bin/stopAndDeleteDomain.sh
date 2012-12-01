#!/bin/bash

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

bash -c "${FILE_PATH}/stopDomain.sh"
test $? -ne 0 && exit 1

bash -c "${FILE_PATH}/deleteDomain.sh"
test $? -ne 0 && exit 1

exit 0
