#!/bin/bash

if [ "${ASADMIN_HOME}" == "" ]
then
	ASADMIN_HOME="${GLASSFISH_HOME}"
fi

if [ "${ASADMIN_HOME}" == "" ]
then
	ASADMIN_HOME="/opt/glassfish/default"
fi

FILE_PATH="$(dirname "$(readlink -f ${BASH_SOURCE[0]})")"

ASADMIN_USER="admin"
ASADMIN_PASSWORD_FILE="${FILE_PATH}/.asadmintruststore"

DOMAIN="teoware"
ASADMIN_CMD="${ASADMIN_HOME}/bin/asadmin --user ${ASADMIN_USER} --passwordfile ${ASADMIN_PASSWORD_FILE}"

JDBC_XML_FILE="${FILE_PATH}/JDBCResource.xml"
CONNECTION_POOL_NAME="RefappDerbyPool"

EAR_FILE="${FILE_PATH}/../code/refapp-ear/target/refapp-ear-0.0.1-SNAPSHOT.ear"
APP_NAME="refapp"
CONTEXT_ROOT="refapp"
