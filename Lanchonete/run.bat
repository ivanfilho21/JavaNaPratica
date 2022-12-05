@echo on
set "var = %cd%"

rmdir /s /q "%~dp0out"

cd src

javac -d ..\out .\main\Main.java
java -classpath ..\out main.Main