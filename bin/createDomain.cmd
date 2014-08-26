@echo off

echo INFO: Creating domain...

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to load env variable
if not (%RETURNVALUE%)==(0) goto ERROR

call %ASADMIN_CMD% create-domain --savemasterpassword true %DOMAIN% > %ERROR_LOG% 2>&1
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to create domain %DOMAIN%
if not (%RETURNVALUE%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: %ERROR_MSG%
exit /B 1

:SUCCESS
echo INFO: Domain %DOMAIN% created successfully
exit /B 0