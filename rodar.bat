@echo off
echo ========================================
echo  Compilando Loja de Material...
echo ========================================

javac -cp "lib\postgresql-42.7.10.jar" -d bin src\*.java

if %errorlevel% neq 0 (
    echo ERRO na compilacao!
    pause
    exit /b 1
)

echo Compilado com sucesso!
echo.
echo ========================================
echo  Iniciando sistema...
echo ========================================

java -cp "bin;lib\postgresql-42.7.10.jar" PrincipalLoja

pause
