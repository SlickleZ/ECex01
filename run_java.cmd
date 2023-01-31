:: Command to compile .java into ./out and run
:: params: only one .java file name

@echo off
@color 0e
@cls

md ".\out" 2>NUL
javac -d .\out %1.java
java -cp .\out %1