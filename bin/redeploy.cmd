@echo off

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd

call %ASADMIN_CMD% redeploy --name %APP_NAME% %EAR_FILE%
