@echo off
call mvn compile
call mvn test
call mvn install
call mvn dependency:tree
