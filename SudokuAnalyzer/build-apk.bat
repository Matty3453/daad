@echo off
echo ====================================
echo   Sudoku Analyzer - Build Script
echo ====================================
echo.

if not exist gradlew.bat (
    echo ERRORE: gradlew.bat non trovato!
    echo Assicurati di essere nella directory del progetto.
    pause
    exit /b 1
)

echo [1/3] Pulizia build precedenti...
call gradlew.bat clean

echo.
echo [2/3] Compilazione e generazione APK...
call gradlew.bat assembleDebug

if %ERRORLEVEL% EQU 0 (
    echo.
    echo [3/3] Build completata con successo!
    echo.
    echo ====================================
    echo APK generato in:
    echo app\build\outputs\apk\debug\app-debug.apk
    echo ====================================
    echo.
    echo Puoi trasferire questo file sul tuo dispositivo Android e installarlo.
) else (
    echo.
    echo [ERRORE] Build fallita!
    echo Controlla gli errori sopra.
)

echo.
pause
