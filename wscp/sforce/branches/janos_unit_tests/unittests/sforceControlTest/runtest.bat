@echo off

set WLS_HOME=c:\BEA_PLATFORM_SP2\weblogic81

set UNITTEST_HOME=c:\BEA_Projects\SForceDemoSVN_Janos\unittests\sforceControlTest
set WSBUILT_CLASS=%UNITTEST_HOME%\.workshop\output\sforceControlTestWeb\WEB-INF\classes\org\controlhaus\sforce\workshop\test\TestSforceControl.class 
set WSNEED_CLASS=%UNITTEST_HOME%\sforceControlTestWeb\WEB-INF\classes\org\controlhaus\sforce\workshop\test\TestSforceControl.class 

set _LIBS=
for %%i in (%WLS_HOME%\server\lib\*.jar) do call cpappend.bat %%i
for %%i in (%WLS_HOME%\common\lib\*.jar) do call cpappend.bat %%i
for %%i in (%WLS_HOME%\p13n\lib\*.jar) do call cpappend.bat %%i
for %%i in (%WLS_HOME%\workshop\wlw-ide.jar) do call cpappend.bat %%i
for %%i in (%WLS_HOME%\workshop\extensions\workspace.jar) do call cpappend.bat %%i
for %%i in (%UNITTEST_HOME%\sforceControlTestWeb\WEB-INF\lib\*.jar) do call cpappend.bat %%i

copy %WSBUILT_CLASS% %WSNEED_CLASS%

ant runtest -verbose

echo %_CMD%

%_CMD%