echo off
set CLASSPATH=lib\log4j-1.2.8;lib\stax-1.1.1-dev.jar;lib\stax-api-1.0.jar;lib\xfire-20050103.051604.jar
set CLASSPATH=%CLASSPATH%;lib\xfire-xmlbeans-20050103.051748.jar;lib\xmlbeans-xmlpublic-2.0-20041214.jar
set CLASSPATH=%CLASSPATH%;lib\xmlbeans-2.0-20041214.jar;lib\beehive-controls-unofficial-1.jar
set CLASSPATH=%CLASSPATH%;lib\controlhaus-amazon-1.0-alpha-3.jar;lib\controlhaus-xfire-client-1.0-alpha-3.jar;
java -cp demo.jar;%CLASSPATH% Demo