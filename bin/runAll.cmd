@echo off

echo Starting full execution...

set FILE_PATH=%~dp0

set STEP=1
call %FILE_PATH%startDomain.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP=2
call %FILE_PATH%deleteJDBCResource.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP=3
call %FILE_PATH%stopAndDeleteDomain.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP=4
call %FILE_PATH%createAndStartDomain.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP=5
call %FILE_PATH%createJDBCResource.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP=6
call %FILE_PATH%createDatabaseSchema.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP=7
call %FILE_PATH%buildAndDeployApplication.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: Execution failed at step '%STEP%'
exit /B 1

:SUCCESS
echo INFO: Execution finished successfully!
exit /B 0