@echo off

echo INFO: Stopping domain...

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to load env variables
if not (%RETURNVALUE%)==(0) goto ERROR

call %ASADMIN_CMD% stop-database > nul 2>&1
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to stop database
if not (%RETURNVALUE%)==(0) goto ERROR

call %ASADMIN_CMD% stop-domain %DOMAIN% > nul 2>&1
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to stop domain '%DOMAIN%'
if not (%RETURNVALUE%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: %ERROR_MSG%
exit /B 1

:SUCCESS
echo INFO: Domain stopped successfully
exit /B 0