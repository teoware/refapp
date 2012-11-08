#!/bin/bash

FILE_PATH="$(dirname "$(readlink -f ${BASH_SOURCE[0]})")"

${FILE_PATH}/createDomain.cmd

${FILE_PATH}/startDomain.cmd
