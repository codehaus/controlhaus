
Welcome to the JDBC Control for Beehive!


Building the JDBC Control
--------------------------

Make sure you have the following on your system:

    * A JDK of version 1.5 or higher
    * A current version of ANT

The following environment variables need to be set:

    * JAVA_HOME - location of 1.5 JDK
    * ANT_HOME - location of ANT installation
    * JDBCCONTROL_HOME - top level directory of the JDBC Control

After the environment variables have been set, run the $JDBCCONTROL_HOME/jdbccontrolEnv.cmd file to setup the JDBC control's
build envirnoment.

ANT build targets are:

    * clean
    * build
    * test

The test target runs JUnit tests on the JDBC control using the Derby database.  Make sure you update your ANT distribution
to include the JUnit task.







