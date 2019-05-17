@echo off
if "%1" == "build" (
    echo Building classes
    javac -d .\classes\ -sourcepath .\srcs\. .\srcs\Main.java
)
if "%1" == "jar" (
    echo Creating jar file
    jar --create --file Menu.jar --main-class Main -C ./classes .
)
if "%1" == "launch" (
    echo Launching project
    java -jar Menu.jar
)

if "%1" == "full" (
    echo Building classes
    javac -d .\classes\ -sourcepath .\srcs\. .\srcs\Main.java
    echo Creating jar file
    jar --create --file Menu.jar --main-class Main -C ./classes .
    echo Launching project
    java -jar Menu.jar
)