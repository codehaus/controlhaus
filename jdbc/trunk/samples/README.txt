Running the JdbcControl Sample Web Application
-----------------------------------------------

The JdbcControl sample requires that the Apache Beehive distribution be installed on your system.

* Set the CATALINA_HOME environment variable to your tomcat installation.

* Add the derby jar file which can be found in this project's source tree to your tomcat installations shared/lib dir.

* Edit the context.xml file's <Context> docbase attribute to set it to the location of this directory.

* Run 'ant deploy' to build and deploy the sample app.

* To start the sample, in your web browser go to: 
   http://localhost:8080/JdbcControlSample 

