#!/bin/bash

FILE_PATH="$(dirname "$(readlink -f ${BASH_SOURCE[0]})")"

${FILE_PATH}/stopDomain.cmd

${FILE_PATH}/deleteDomain.cmd
