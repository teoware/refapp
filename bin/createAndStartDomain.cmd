@echo off

set FILE_PATH=%~dp0

call %FILE_PATH%createDomain.cmd

call %FILE_PATH%startDomain.cmd
