/*
 * Copyright 2004 BEA Systems, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.controlhaus.jdbc;

import org.apache.beehive.controls.api.bean.AnnotationConstraints;
import org.apache.beehive.controls.api.bean.AnnotationMemberTypes;
import org.apache.beehive.controls.api.bean.ControlInterface;
import org.apache.beehive.controls.api.properties.PropertySet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

/**
 * Simplifies access to a relational database from your Java code using SQL commands.
 * The Jdbc Control handles the work of connecting to, sending queries to, and ResultSet mapping from
 * the database. You don't need to know how to use JDBC in order to use the Jdbc Control, just basic SQL.
 * <p/>
 * To use a Jdbc Control create a .jcx file (java file with a .jcx extension) which extends this interface.
 * Add annotations to the jcx to tell the Jdbc Control how to connect to your database instance (either
 * ConnectionDataSource or ConnectionDriver), then add methods which include SQL annotations to access the database.
 */
@ControlInterface
public interface JdbcControl {

    /**
     * Interface for a user defined Jndi Context factory which can be used
     * as a value for the jndiContextFactory member of the ConnectionDataSource
     * annotation.
     */
    public interface IJndiContextFactory {

        /**
         * Get a JNDI InitialContext instance.
         * @return InitialContext instance
         * @throws NamingException if context could not be found.
         */
        public InitialContext getInitialContext() throws NamingException;
    }

    /**
     * Class-level annotation for making a DataSource available for use with the Jdbc Control. Either this annotation or
     * the ConnectionDriver annotation must be set for a jcx which extends the JdbcControl interface.
     */
    @PropertySet
    @Inherited
    @AnnotationConstraints.AllowExternalOverride
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})
    public @interface ConnectionDataSource {

        /** The jndi name of the DataSource. This is a required element for this annotation. */
        @AnnotationMemberTypes.JndiName(resourceType = AnnotationMemberTypes.JndiName.ResourceType.DATASOURCE)
        String jndiName();

        /** The name of a class which implements the IJndiContextFactory interface. This is an optional element of this annotation. */
        Class<? extends IJndiContextFactory> jndiContextFactory() default DefaultJndiContextFactory.class;
    }

    /**
     * Class-level annotation for making a ConnectionDriver available for use with the Jdbc Control. Either this
     * annotation or the ConnectionDataSource annotation must be set for a jcx which extends the JdbcControl interface.
     * See java.sql.DatabaseConnection for additional information about the elements of this annotation.
     */
    @PropertySet
    @Inherited
    @AnnotationConstraints.AllowExternalOverride
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})
    public @interface ConnectionDriver {
        /** A String containing the fully qualified name of the database driver class. Required element. */
        String databaseDriverClass();

        /** A String containing the database URL to connect to. Required element. */
        String databaseURL();

        /** A String containing the user name to connect to the database as. Optional element. */
        String userName() default "";

        /** A String containing the password associated with userName. Optional element. */
        String password() default "";

        /** A String containing a comma seperated list of name/value pairs for the DatabaseConnection. Optional element. */
        String properties() default "";
    }

    /**
     * Returns a database connection to the server associated
     * with the control. It is typically not necessary to call this method
     * when using the control.
     * @return A Connection a database.
     */
    public Connection getConnection() throws SQLException;

    /**
     * This constant can be used as the value for the maxRows element of the SQL annotation.
     * It indicates that all rows should be returned (i.e. no limit)
     */
    public final int MAXROWS_ALL = -1;

    /**
     * Default value for the iteratorElementType element of the
     * SQL annotation.  It signals that no type has been defined for the method
     * (common if the method return type isn't itself an iterator)
     */
    public interface UndefinedIteratorType {
    }

    /**
     * Default value for the resultSetMapper element of the
     * SQL annotation.  It signals that no type has been defined for the method.
     */
    public interface UndefinedResultSetMapper {
    }

    /**
     * Method-level annotation for methods in a jcx which wish to access a database instance.
     */
    @PropertySet
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @AnnotationConstraints.AllowExternalOverride
    @Target({ElementType.METHOD})
    public @interface SQL {
        /** The SQL statement to send to the database. Required annotation element */
        String statement();

        /** Maximum array length.
         * This element has no effect on the call unless the method return type is an array.
         * Optional element.
         */
        int arrayMaxLength()            default 1024;

        /** Max number of ResultSet rows to return.
         * If used with arrayMaxLength the smaller value is used.
         * Optional element, default value is no limit on number of rows returned.
         */
        int maxRows()                   default MAXROWS_ALL;

        /** Execute the SQL statement as a batch update.
         * Methods which have this element set to true must return an array of ints.
         * Optional element, defaults to false.
         */
        @AnnotationMemberTypes.Optional
        boolean batchUpdate()           default false;

        /** Return the generated key values generated by the SQL statement. Optional element, defaults to false. */
        @AnnotationMemberTypes.Optional
        boolean getGeneratedKeys()      default false;

        /** Specify generated key columns by column names to return when the getGeneratedKeys element is true.
         *  May only be set if getGeneratedKeys is set to true, otherwise a compile time error is generated.
         * Optional element.
         */
        @AnnotationMemberTypes.Optional
        String[] generatedKeyColumnNames()  default {};

        /** Specify generated key columns by column number to return when the getGeneratedKeys element is true.
         *  May only be set if getGeneratedKeys is set to true, otherwise a compile time error is generated
         *  Optional element.
         */
        @AnnotationMemberTypes.Optional
        int[] generatedKeyColumnIndexes()  default {};

        /** Specify a custom result set mapper for the ResultSet generated by the SQL statement.
         *  ResultSet mappers must extend the ResultSetMapper abstract base class.  If a value is specified
         *  it will be used to map the ResultSet of the query to the return type of the method.
         *  See org.controlhaus.jdbc.ResultSetMapper for additional information.
         *  Optional element.
         */
        @AnnotationMemberTypes.Optional
        Class resultSetMapper()         default UndefinedResultSetMapper.class;

        /** Specify the type of element to be interated over when the method's return type is java.util.Iterator.
         *  Optional element.
         */
        @AnnotationMemberTypes.Optional
        Class iteratorElementType()     default UndefinedIteratorType.class;
    }

    /**
     * Sets the Calendar instance that should be used when setting and getting
     * {@link java.sql.Date Date}, {@link java.sql.Time Time}, and
     * {@link java.sql.Timestamp Timestamp} values.
     *
     * @see java.sql.ResultSet#getDate(int, Calendar) java.sql.ResultSet#getDate(int, Calendar)
     * @see java.sql.ResultSet#getTime(int, Calendar) java.sql.ResultSet#getTime(int, Calendar)
     * @see java.sql.ResultSet#getTimestamp(int, Calendar) java.sql.ResultSet#getTimestamp(int, Calendar)
     * @see java.sql.PreparedStatement#setDate(int, java.sql.Date, Calendar) java.sql.PreparedStatement#setDate(int, Date, Calendar)
     * @see java.sql.PreparedStatement#setTime(int, java.sql.Time, Calendar) java.sql.PreparedStatement#setTime(int, Time, Calendar)
     * @see java.sql.PreparedStatement#setTimestamp(int, java.sql.Timestamp, Calendar) java.sql.PreparedStatement#setTimestamp(int, Timestamp, Calendar)
     */
    public void setDataSourceCalendar(Calendar cal);

    /**
     * Gets the Calendar instance used when setting and getting
     * {@link java.sql.Date Date}, {@link java.sql.Time Time}, and
     * {@link java.sql.Timestamp Timestamp} values. This is the Calendar
     * set by the setDataSourceCalendar method.
     *
     * @return The Calendar instance.
     */
    public Calendar getDataSourceCalendar();

    /**
     * Nested class used for specifing parameters for a callable statement.  If a method in a .jcx takes an array of
     * SQLParameter, the JdbcControl treats the SQL as a CallableStatement and inserts values into the statement from
     * the SQLParameter array.  After the CallableStatement executes, results are mapped into OUT type parameters found
     * int the SQLParameter array.
     */
    public static class SQLParameter {
        /** IN direction constant. */
        public static final int IN = 1;
        /** OUT direction constant. */
        public static final int OUT = 2;
        /** IN and OUT directions constant. */
        public static final int INOUT = IN | OUT;

        /** Parameter value. */
        public Object value = null;
        /** Parameter SQL data type. See java.sql.Types. */
        public int type = Types.NULL;
        /** Parameter direction. */
        public int dir = IN;

        /**
         * Create a new SQLParameter with the specified value.
         * @param value The parameter value.
         */
        public SQLParameter(Object value) {
            this.value = value;
        }

        /**
         * Create a new SQLParameter with the specified value and SQL data type.
         * @param value The parameter value.
         * @param type SQL data type.
         */
        public SQLParameter(Object value, int type) {
            this(value);
            this.type = type;
        }

        /**
         * Create a new SQLParameter with the specified value, SQL data type and direction.
         * @param value The parameter value.
         * @param type SQL data type.
         * @param dir IN / OUT or INOUT
         */
        public SQLParameter(Object value, int type, int dir) {
            this(value, type);
            this.dir = dir;
        }

        /**
         * Clone this parameter.
         * @return A copy of this parameter.
         */
        public Object clone() {
            return new SQLParameter(value, type, dir);
        }
    }
}
