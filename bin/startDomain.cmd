@echo off

echo INFO: Starting domain...

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to load env variables
if not (%RETURNVALUE%)==(0) goto ERROR

call %ASADMIN_CMD% start-database > %ERROR_LOG% 2>&1
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to start database
if not (%RETURNVALUE%)==(0) goto ERROR

call %ASADMIN_CMD% start-domain %DOMAIN% > %ERROR_LOG% 2>&1
set RETURNVALUE=%ERRORLEVEL%
if (%RETURNVALUE%)==(1) (
	findstr "There is a process already using the admin port 4848" %ERROR_LOG% > nul 2>&1
	set RETURNVALUE=%ERRORLEVEL%
)
set ERROR_MSG=Failed to start domain
if not (%RETURNVALUE%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: %ERROR_MSG%
exit /B 1

:SUCCESS
echo INFO: Domain started successfully
exit /B 0