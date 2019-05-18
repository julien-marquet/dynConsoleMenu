@echo off
if "%1" == "build" (
    set CLASSPATH="G:\Repos\dynConsoleMenu\build\libs"
    CALL :build
)
if "%1" == "jar" (
    CALL :jar
)
if "%1" == "launch" (
    CALL :launch
)

if "%1" == "full" (
    CALL :build
    CALL :jar
    CALL :launch
)

EXIT /B %ERRORLEVEL% 

:build 
    echo Building classes
    javac -d .\classes\ -sourcepath .\srcs\. -classpath ..\build\libs\dynConsoleMenu.jar .\srcs\Test.java
EXIT /B 0

:jar
    echo Creating jar file
    cd classes
    jar xf ..\..\build\libs\*.jar
    cd ..
    jar --create --file Test.jar --main-class Test -C ./classes .
EXIT /B 0

:launch
    echo Launching project
    java -jar Test.jar
EXIT /B 0