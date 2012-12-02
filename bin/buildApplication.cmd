@echo off

echo INFO: Building application...

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to load env variables
if not (%RETURNVALUE%)==(0) goto ERROR

set POM_FILE=%FILE_PATH%..\code\pom.xml
set ERROR_MSG=Failed to find Maven build file
if not exist %POM_FILE% goto ERROR

call %MAVEN_CMD% -q -f %POM_FILE% clean install > %ERROR_LOG% 2>&1
set RETURNVALUE=%ERRORLEVEL%
set ERROR_MSG=Failed to build application using Maven
if not (%RETURNVALUE%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: %ERROR_MSG%
exit /B 1

:SUCCESS
echo INFO: Application built successfully
exit /B 0
