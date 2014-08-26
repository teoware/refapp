@echo off

set FILE_PATH=%~dp0

call %FILE_PATH%buildApplication.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

call %FILE_PATH%redeployApplication.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

goto SUCCESS

:ERROR
exit /B 1

:SUCCESS
exit /B 0