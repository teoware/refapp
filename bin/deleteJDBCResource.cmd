@echo off

echo INFO: Deleting JDBC resources...

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to load env variables
if not (%RETURNVALUE%)==(0) goto ERROR

call %ASADMIN_CMD% delete-jdbc-connection-pool --cascade true %CONNECTION_POOL_NAME% > %ERROR_LOG% 2>&1
set RETURNVALUE=%ERRORLEVEL%
if (%RETURNVALUE%)==(1) (
	findstr "%CONNECTION_POOL_NAME% does not exist" %ERROR_LOG% > nul 2>&1
	set RETURNVALUE=%ERRORLEVEL%
)
set ERROR_MSG=Failed to delete JDBC resources
if not (%RETURNVALUE%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: %ERROR_MSG%
exit /B 1

:SUCCESS
echo INFO: JDBC resources deleted successfully
exit /B 0