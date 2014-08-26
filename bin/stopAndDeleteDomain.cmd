@echo off

set FILE_PATH=%~dp0

call %FILE_PATH%stopDomain.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

call %FILE_PATH%deleteDomain.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

goto SUCCESS

:ERROR
exit /B 1

:SUCCESS
exit /B 0