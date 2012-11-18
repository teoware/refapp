@echo off

echo INFO: Redeploying application...

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to load env variables
if not (%RETURNVALUE%)==(0) goto ERROR

set ERROR_MSG=Failed to find application EAR file
if not exist %EAR_FILE% goto ERROR

call %ASADMIN_CMD% redeploy --name %APP_NAME% %EAR_FILE% > %ERROR_LOG% 2>&1
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to redeploy application
if not (%RETURNVALUE%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: %ERROR_MSG%
exit /B 1

:SUCCESS
echo INFO: Application redeployed successfully
exit /B 0