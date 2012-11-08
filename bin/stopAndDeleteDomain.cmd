@echo off

set FILE_PATH=%~dp0

call %FILE_PATH%stopDomain.cmd

call %FILE_PATH%deleteDomain.cmd
