@echo off

echo INFO: Deleting database folder...

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to load env variables
if not (%RETURNVALUE%)==(0) goto ERROR

if not exist %DATABASE_FOLDER% goto SUCCESS

call rd /s /q %DATABASE_FOLDER% > %ERROR_LOG% 2>&1
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to delete database folder
if not (%RETURNVALUE%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: %ERROR_MSG%
exit /B 1

:SUCCESS
echo INFO: Database folder deleted successfully
exit /B 0