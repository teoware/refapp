@echo off

echo INFO: Deleting domain...

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to load env variables
if not (%RETURNVALUE%)==(0) goto ERROR

call %ASADMIN_CMD% delete-domain %DOMAIN% > %ERROR_LOG% 2>&1
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to delete domain '%DOMAIN%'
if not (%RETURNVALUE%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: %ERROR_MSG%
exit /B 1

:SUCCESS
echo INFO: Domain '%DOMAIN%' deleted successfully
exit /B 0