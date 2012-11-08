@echo off

if not (%ASADMIN_HOME%)==() goto NEXT
set ASADMIN_HOME="C:\Tech\GlassFish\glassfish-3.1.2.2"

if (%GLASSFISH_HOME%)==() goto NEXT
set ASADMIN_HOME=%GLASSFISH_HOME%

:NEXT

set FILE_PATH=%~dp0

set ASADMIN_USER="admin"
set ASADMIN_PASSWORD_FILE="%FILE_PATH%.asadmintruststore"

set DOMAIN="teoware"
set ASADMIN_CMD=%ASADMIN_HOME%\bin\asadmin --user %ASADMIN_USER% --passwordfile %ASADMIN_PASSWORD_FILE%

set JDBC_XML_FILE="%FILE_PATH%JDBCResource.xml"
set CONNECTION_POOL_NAME="jdbc/refapp-pool"

set EAR_FILE="%FILE_PATH%..\code\refapp-ear\target\refapp-ear-0.0.1-SNAPSHOT.ear"
set APP_NAME="refapp"
set CONTEXT_ROOT="refapp"
