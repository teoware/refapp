@echo off

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd

call %ASADMIN_CMD% create-domain --savemasterpassword true %DOMAIN%
