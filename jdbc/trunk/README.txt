
Welcome to the JDBC Control for Beehive!

Building the JDBC Control
--------------------------

Make sure you have the following on your system:

    * A JDK of version 1.5 or higher
    * A current version of ANT
    * Current Beehive build

The following environment variables need to be set:

    * JAVA_HOME - location of 1.5 JDK
    * ANT_HOME - location of ANT installation
    * BEEHIVE_HOME - location of the Beehive distribution
      (in the beehive source tree use: <beehive_basedir>/build/dist/apache-beehive-incubating-svn-snapshot)

ANT build targets are:

    * clean
    * build
    * test
    * download-dependencies
    * check-dependencies

The download-dependencies task will download all external libraries required for building the control from the Maven
repository. You can run 'check-dependencies' to see a list of what will be downloaded.  These libraries can also be
downloaded manually if you prefer.

The test target runs JUnit tests on the JDBC control using the Derby database.  Make sure you update your ANT distribution
to include the JUnit task.
