@echo off

echo INFO: Creating JDBC resources...

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to load env variables
if not (%RETURNVALUE%)==(0) goto ERROR

set ERROR_MSG=Failed to find JDBC resource config file
if not exist %JDBC_XML_FILE% goto ERROR

call %ASADMIN_CMD% add-resources %JDBC_XML_FILE% > nul 2>&1
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to create JDBC resources
if not (%RETURNVALUE%)==(0) goto ERROR

call %ASADMIN_CMD% ping-connection-pool %CONNECTION_POOL_NAME% > nul 2>&1
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to ping JDBC connection pool
if not (%RETURNVALUE%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: %ERROR_MSG%
exit /B 1

:SUCCESS
echo INFO: JDBC resources created successfully
exit /B 0