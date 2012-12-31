@echo off

echo INFO: Starting full execution...

set FILE_PATH=%~dp0

set STEP="1 (startDomain)"
call %FILE_PATH%startDomain.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP="2 (deleteJDBCResource)"
call %FILE_PATH%deleteJDBCResource.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP="3 (stopDomain)"
call %FILE_PATH%stopDomain.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP="4 (deleteDomain)"
call %FILE_PATH%deleteDomain.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP="5 (deleteDatabase)"
call %FILE_PATH%deleteDatabase.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP="6 (createDomain)"
call %FILE_PATH%createDomain.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP="7 (startDomain)"
call %FILE_PATH%startDomain.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP="8 (createJDBCResource)"
call %FILE_PATH%createJDBCResource.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP="9 (createDatabaseSchema)"
call %FILE_PATH%createDatabaseSchema.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP="10 (buildApplication)"
call %FILE_PATH%buildApplication.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

set STEP="11 (deployApplication)"
call %FILE_PATH%deployApplication.cmd
if not (%ERRORLEVEL%)==(0) goto ERROR

goto SUCCESS

:ERROR
echo ERROR: Execution failed at step %STEP%
exit /B 1

:SUCCESS
echo INFO: Execution finished successfully!
exit /B 0