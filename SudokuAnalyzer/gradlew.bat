@echo off
setlocal

set GRADLE_USER_HOME=%USERPROFILE%\.gradle

if not exist "%GRADLE_USER_HOME%" mkdir "%GRADLE_USER_HOME%"

set GRADLE_WRAPPER_JAR=%~dp0gradle\wrapper\gradle-wrapper.jar
set GRADLE_PROPERTIES=%~dp0gradle\wrapper\gradle-wrapper.properties

if not exist "%GRADLE_WRAPPER_JAR%" (
    echo Gradle Wrapper not found. Please run this from a proper Gradle project.
    exit /b 1
)

java -jar "%GRADLE_WRAPPER_JAR%" %*
