@echo off

echo INFO: Undeploying application...

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to load env variables
if not (%RETURNVALUE%)==(0) goto ERROR

call %ASADMIN_CMD% deploy --contextroot %CONTEXT_ROOT% --name %APP_NAME% %DEPLOY_FILE% > nul 2>&1
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to deploy application
if not (%RETURNVALUE%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: %ERROR_MSG%
exit /B 1

:SUCCESS
echo INFO: Application undeployed successfully
exit /B 0