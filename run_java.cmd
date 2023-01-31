@echo off
@color 0e
@cls

md ".\out" 2>NUL
javac -d .\out %1.java
java -cp .\out %1