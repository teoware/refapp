@echo off

if not (%1)==() set ASADMIN_HOME=%1
if not (%ASADMIN_HOME%)==() goto NEXT
set ASADMIN_HOME=%GLASSFISH_HOME%
if not (%ASADMIN_HOME%)==() goto NEXT
set ASADMIN_HOME="C:\Tech\GlassFish\glassfish-3.1.1"

:NEXT

if not (%2)==() set MAVEN_HOME=%2
if not (%MAVEN_HOME%)==() goto READY
set MAVEN_HOME=%MVN_HOME%
if not (%MAVEN_HOME%)==() goto READY
set MAVEN_HOME=%M2_HOME%
if not (%MAVEN_HOME%)==() goto READY

echo Maven home not set. Unable to continue.
goto ERROR

:READY

set FILE_PATH=%~dp0

set ASADMIN_USER="admin"
set ASADMIN_PASSWORD_FILE="%FILE_PATH%..\conf\.asadmintruststore"

set DOMAIN="domain1"
set ASADMIN_CMD=%ASADMIN_HOME%\bin\asadmin --user %ASADMIN_USER% --passwordfile %ASADMIN_PASSWORD_FILE%

set MAVEN_CMD=%MAVEN_HOME%\bin\mvn

set JDBC_XML_FILE="%FILE_PATH%..\conf\jdbc_resource.xml"
set CONNECTION_POOL_NAME="RefappDerbyPool"

set DEPLOY_NAME=refapp-interfaces-webui-0.0.1-SNAPSHOT
set DEPLOY_FILE="%FILE_PATH%..\code\refapp-interfaces-webui\target\%DEPLOY_NAME%.war"
set APP_NAME="refapp"
set CONTEXT_ROOT="refapp"

for /f "tokens=2,3,4 delims=/ " %%f in ('date /t') do set d=%%h.%%g.%%f
for /f "tokens=1,2 delims=: " %%f in ('time /t') do set t=%%f.%%g
set TIMESTAMP=%d%T%t%

rem set ERROR_LOG=%TEMP%\%APP_NAME%-%TIMESTAMP%.error
set ERROR_LOG=%TEMP%\%APP_NAME%.error

goto SUCCESS

:ERROR
exit /B 1

:SUCCESS
exit /B 0
