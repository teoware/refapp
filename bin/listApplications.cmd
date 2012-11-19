@echo off

echo INFO: Listing applications...

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to load env variables
if not (%RETURNVALUE%)==(0) goto ERROR

call %ASADMIN_CMD% list-applications
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to list applications
if not (%RETURNVALUE%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: %ERROR_MSG%
exit /B 1

:SUCCESS
echo INFO: Application listed successfully
exit /B 0