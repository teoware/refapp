@echo off

set FILE_PATH=%~dp0

call %FILE_PATH%env.cmd

call %ASADMIN_CMD% delete-jdbc-connection-pool --cascade true %CONNECTION_POOL_NAME%
