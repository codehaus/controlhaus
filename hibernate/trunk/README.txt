[BUILDING WITH MAVEN]
To build with maven first build the Beehive distribution and put it in your
local repository (usually at ~/.maven/repository) as 
"beehive/jars/beehive-1.0-alpha-1-SNAPSHOT.jar".  Currently there is no release
or even a release version, so I made one up. Then just run maven:

$ maven jar:install

[USING]
Using this control should be fairly easy if you are familiar with Hibernate.
It will look for your hibernate configuration on the classpath as "/hibernate.cfg.xml".

To then use the control declare it in your class:

@Control HibernateControl control;

You can then access the session from the control.