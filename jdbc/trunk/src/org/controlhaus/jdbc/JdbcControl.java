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
 * The Database control handles the work of connecting to the database,
 * which makes it simpler to use than JDBC.
 * <br/><br/>
 *
 * The best way to use a Database control is to
 * add it to your design, then add to the control methods that capture the
 * specific SQL expressions you want to execute against the database. Tasks
 * such as getting a database connection are handled for you as you use the
 * control.
 */
@ControlInterface
public interface JdbcControl {

    public interface IJndiContextFactory {
        public InitialContext getInitialContext() throws NamingException;
    }

    /**
     * ConnectionDataSource Annotation Declaration
     */
    @PropertySet
    @Inherited
    @AnnotationConstraints.AllowExternalOverride
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})
    public @interface ConnectionDataSource {
        @AnnotationMemberTypes.JndiName(resourceType = AnnotationMemberTypes.JndiName.ResourceType.DATASOURCE)
        Class<? extends IJndiContextFactory> jndiContextFactory() default DefaultJndiContextFactory.class;
        String jndiName();   // no default ... value is required
    }

    /**
     * ConnectionDriver Annotation Declaration
     */
    @PropertySet
    @Inherited
    @AnnotationConstraints.AllowExternalOverride
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD})
    public @interface ConnectionDriver {
        String databaseDriverClass();   // no default, value is required
        String databaseURL();           // no default, value is required
        String userName() default "";
        String password() default "";
        String properties() default "";
    }

    /**
     * Returns a database connection to the server associated
     * with the control. It is typically not necessary to call this method
     * when using the control.
     */
    public Connection getConnection() throws SQLException;

    /**
     * This constant can be used as the value for the maxRows member of the SQL PropertySet.
     * It indicates that all rows should be returned (i.e. no limit)
     */
    public final int MAXROWS_ALL = -1;

    /**
     * This class acts as a default value for the iteratorElementType member of the
     * SQL PropertySet.  It signals that no type has been defined for the method
     * (common if the method return type isn't itself an iterator)
     */
    public interface UndefinedIteratorType {
    }

    /**
     * This class acts as a default value for the resultSetMapper member of the
     * SQL PropertySet.  It signals that no type has been defined for the method
     */
    public interface UndefinedResultSetMapper {
    }

    /**
     * SQL Annotation definition.
     */
    @PropertySet
    @Inherited
    @Retention(RetentionPolicy.RUNTIME)
    @AnnotationConstraints.AllowExternalOverride
    @Target({ElementType.METHOD})
    public @interface SQL {
        /** statement - The SQL statement - may not be empty */ 
        String statement()              default "";

        /** arrayMaxLength - Maximum array lenght (when method return type is array) */
        int arrayMaxLength()            default 1024;

        /** maxRows - Max number of ResultSet rows to return -- if used with arrayMaxLength the smaller value is used. */
        int maxRows()                   default MAXROWS_ALL;

        /** batchUpdate - if true execute the SQL as a batch update */
        @AnnotationMemberTypes.Optional
        boolean batchUpdate()           default false;

        /** getGeneratedkeys - if true return the keys values generated by the SQL */
        @AnnotationMemberTypes.Optional
        boolean getGeneratedKeys()      default false;

        /** generatedKeyColumnNames - specifies generated key columns by column names to return, may only be set if getGeneratedKeys is set to true */
        @AnnotationMemberTypes.Optional
        String[] generatedKeyColumnNames()  default {};

        /** generatedKeyColumnIndexes - specifies generated key columns by column number to return, may only be set if getGeneratedKeys is set to true */
        @AnnotationMemberTypes.Optional
        int[] generatedKeyColumnIndexes()  default {};

        /** resultSetMapper - specifies a custom result set mapper for the ResultSet generated by the SQL */
        @AnnotationMemberTypes.Optional
        Class resultSetMapper()         default UndefinedResultSetMapper.class;

        /** iteratorElementType - specifies the type of element to be interated over when the method's return type is java.util.Iterator */
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
     * Class used for specifing parameters for a callable statement,
     * if used as a method arg, overrides statement subs
     */
    public static class SQLParameter {
        public static final int IN = 1;
        public static final int OUT = 2;
        public static final int INOUT = IN | OUT;

        public Object value = null;
        public int type = Types.NULL;
        public int dir = IN;

        public SQLParameter(Object value) {
            this.value = value;
        }

        public SQLParameter(Object value, int type) {
            this.value = value;
            this.type = type;
        }

        public SQLParameter(Object value, int type, int dir) {
            this.value = value;
            this.type = type;
            this.dir = dir;
        }

        public Object clone() {
            return new SQLParameter(value, type, dir);
        }
    }
}
