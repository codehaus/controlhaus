[BUILDING WITH MAVEN]
To build with maven first build the Beehive distribution and put it in your
local repository (usually at ~/.maven/repository) as 
"beehive/jars/beehive-1.0-alpha-1-SNAPSHOT.jar".  Currently there is no release
or even a release version, so I made one up. Then just run maven:

$ maven jar:install

[BUILDING WITH ANT]
To build with ant you need to create a "lib/" directory and place the
controls jar from the beehive build in it.  The rest of the dependencies
should download.  The run ant:

$ ant build
