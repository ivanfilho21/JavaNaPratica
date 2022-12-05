@echo off

:: Declarar as variáveis
set BASE_DIR=%~dp0
set OUTPUT_DIR="%BASE_DIR%out\"
set MAIN_CLASS_DIR=principal
set MAIN_CLASS_NAME=Principal

:: Remover o diretório de saída do Java, caso exista

if exist %OUTPUT_DIR% (
    rmdir /s /q %OUTPUT_DIR%
)

:: Compilar o arquivo principal do projeto
cd src\
javac -d ..\out\ .\%MAIN_CLASS_DIR%\%MAIN_CLASS_NAME%.java

:: Executar a classe principal
java -classpath ..\out\ %MAIN_CLASS_DIR%.%MAIN_CLASS_NAME%