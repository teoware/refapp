#!/bin/bash

test "$1" != "" && ASADMIN_HOME="$1"
test "${ASADMIN_HOME}" == "" && ASADMIN_HOME="${GLASSFISH_HOME}"
test "${ASADMIN_HOME}" == "" && ASADMIN_HOME="/opt/glassfish/default"
test "$2" != "" && MAVEN_HOME="$2"
test "${MAVEN_HOME}" == "" && MAVEN_HOME="${MVN_HOME}"
test "${MAVEN_HOME}" == "" && MAVEN_HOME="${M2_HOME}"
test "${MAVEN_HOME}" == "" && MAVEN_HOME="/opt/maven/default"

FILE_PATH="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

ASADMIN_USER="admin"
ASADMIN_PASSWORD_FILE="${FILE_PATH}/../conf/.asadmintruststore"

DOMAIN="domain1"
DOMAIN_FOLDER="${ASADMIN_HOME}/glassfish/domains/${DOMAIN}"
ASADMIN_CMD="${ASADMIN_HOME}/bin/asadmin --user ${ASADMIN_USER} --passwordfile ${ASADMIN_PASSWORD_FILE}"

MAVEN_CMD="${MAVEN_HOME}/bin/mvn"

DATABASE_FOLDER="${ASADMIN_HOME}/glassfish/databases/refapp"

JDBC_XML_FILE="${FILE_PATH}/../conf/jdbc_resource.xml"
CONNECTION_POOL_NAME="RefappDerbyPool"

DEPLOY_NAME="refapp-interfaces-webui-0.0.1-SNAPSHOT"
DEPLOY_FILE="${FILE_PATH}/../code/refapp-interfaces-webui/target/${DEPLOY_NAME}.war"
APP_NAME="refapp"
CONTEXT_ROOT="refapp"

TIMESTAMP=$(date +"%Y.%m.%dT%H.%M")
#ERROR_LOG="/tmp/${APP_NAME}-${TIMESTAMP}.error"
ERROR_LOG="/tmp/${APP_NAME}.error"
