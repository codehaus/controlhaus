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

package org.controlhaus.jdbc.parser;

import org.apache.beehive.controls.api.ControlException;
import org.apache.beehive.controls.api.context.ControlBeanContext;
import org.controlhaus.jdbc.JdbcControl;
import org.controlhaus.jdbc.TypeMappingsFactory;

import javax.sql.RowSet;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Calendar;

/**
 * Represents a fully parsed SQL statement. SqlStatements can be used to generated a java.sql.PreparedStatement.
 */
public final class SqlStatement extends SqlFragmentContainer implements Serializable {

    private static final TypeMappingsFactory _tmf = TypeMappingsFactory.getInstance();
    private boolean _callableStatement = false;
    private boolean _cacheableStatement = true;

    //
    // set from SQL annotation element values
    //
    private boolean _batchUpdate;
    private boolean _getGeneratedKeys;
    private String[] _genKeyColumnNames;
    private int _fetchSize;
    private int _maxArray;
    private int _maxRows;
    private int[] _genKeyColumnIndexes;
    private JdbcControl.ScrollType _scrollType;
    private JdbcControl.FetchDirection _fetchDirection;
    private JdbcControl.HoldabilityType _holdability;

    /**
     * Create a new SqlStatement.
     */
    SqlStatement() {
        super();
    }

    /**
     * Append a SqlFragment to the end of this statement.
     *
     * @param frag SqlFragment to append.
     */
    void addChild(SqlFragment frag) {
        super.addChild(frag);

        if (frag.isDynamicFragment()) {
            _cacheableStatement = false;
        }
    }


    /**
     * Can the PreparedStatement generated by this class be cached?
     *
     * @return true if this statement can be cached by the SqlParser.
     */
    boolean isCacheable() { return _cacheableStatement; }


    /**
     * Does this statement generate a callable or prepared statement?
     *
     * @return true if this statement generates callable statement.
     */
    public boolean isCallableStatement() { return _callableStatement; }

    /**
     * Does this statement do a batch update?
     *
     * @return true if this statement should be executed as a batch update.
     */
    public boolean isBatchUpdate() { return _batchUpdate; }

    /**
     * Does this statement return generatedKeys?
     *
     * @return true if getGeneratedKeys set to true.
     */
    public boolean getsGeneratedKeys() { return _getGeneratedKeys; }

    /**
     * Generates the PreparedStatement the SQL statement.
     *
     * @param context    ControlBeanContext instance.
     * @param connection Connection to database.
     * @param calendar   Calendar instance which can be used to resolve date/time values.
     * @param method     Method the SQL is associated with.
     * @param arguments  Method parameters.
     * @return The PreparedStatement generated by this statement.
     * @throws SQLException If PreparedStatement cannot be created.
     */
    public PreparedStatement createPreparedStatement(ControlBeanContext context, Connection connection,
                                                     Calendar calendar, Method method, Object[] arguments)
            throws SQLException {

        PreparedStatement preparedStatement = null;
        loadSQLAnnotationStatmentOptions(context, method);
        checkJdbcSupport(connection.getMetaData());

        _callableStatement = setCallableStatement(arguments);

        try {
            final String sql = getPreparedStatementText(context, method, arguments);

            //
            // is this a request for generatedKeys ?
            //
            if (_getGeneratedKeys) {

                if (_callableStatement) {
                    throw new ControlException("getGeneratedKeys not supported for CallableStatements");
                }

                if (_genKeyColumnNames.length > 0) {
                    preparedStatement = connection.prepareStatement(sql, _genKeyColumnNames);
                } else if (_genKeyColumnIndexes.length > 0) {
                    preparedStatement = connection.prepareStatement(sql, _genKeyColumnIndexes);
                } else {
                    preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                }

            } else {
                preparedStatement = (_callableStatement)
                        ? connection.prepareCall(sql, _scrollType.getType(), _scrollType.getConcurrencyType(), _holdability.getHoldability())
                        : connection.prepareStatement(sql, _scrollType.getType(), _scrollType.getConcurrencyType(), _holdability.getHoldability());
            }

            //
            // If the method argument is of type SQLParameter, treat this statement as a CallableStatement,
            //
            if (_callableStatement) {
                for (SqlFragment sf : _children) {
                    if (sf.hasParamValue()) {
                        throw new ControlException("Cannot use parameter substution and SQLParameter array in the same method.");
                    }
                }
                JdbcControl.SQLParameter[] params = (JdbcControl.SQLParameter[]) arguments[0];
                if (params == null) {
                    return preparedStatement;
                }
                for (int i = 0; i < params.length; i++) {
                    JdbcControl.SQLParameter p = params[i];
                    if (p.dir != JdbcControl.SQLParameter.OUT) {
                        Object value = params[i].value;
                        setPreparedStatementParameter(preparedStatement, i + 1, value, params[i].type, calendar);
                    }

                    if (p.dir != JdbcControl.SQLParameter.IN) {
                        ((CallableStatement) preparedStatement).registerOutParameter(i + 1, params[i].type);
                    }
                }

                //
                // special handling for batch updates
                //
            } else if (_batchUpdate) {
                doBatchUpdate(preparedStatement, arguments, calendar);
                //
                // standard case, not a batch or callable
                //
            } else {
                int pIndex = 1;
                for (SqlFragment sf : _children) {
                    if (sf.hasParamValue()) {
                        Object values[] = sf.getParameterValues(context, method, arguments);
                        for (Object value : values) {
                            setPreparedStatementParameter(preparedStatement, pIndex++, value, sf.getParamSqlDataType(), calendar);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            if (preparedStatement != null) preparedStatement.close();
            throw e;
        }

        preparedStatement.setFetchDirection(_fetchDirection.getDirection());
        preparedStatement.setFetchSize(_fetchSize);
        preparedStatement.setMaxRows(computeMaxRows(method));

        return preparedStatement;
    }



    // /////////////////////////////////////////////////// PRIVATE METHODS ///////////////////////////////////////////

    /**
     * Sets the specified parameter in the prepared statement.
     *
     * @param ps      A PreparedStatement.
     * @param i       index of parameter to set.
     * @param value   value of the parameter.
     * @param sqlType SQL type of value.
     * @param cal     A calendar instance used to resolve date/time values.
     * @throws SQLException If the parameter cannot be set.
     */
    private void setPreparedStatementParameter(PreparedStatement ps, int i, Object value, int sqlType, Calendar cal)
            throws SQLException {

        if (sqlType == Types.NULL) {
            sqlType = _tmf.getSqlType(value);
        }

        if (value == null) {
            ps.setNull(i, Types.NULL == sqlType ? Types.VARCHAR : sqlType);
            return;
        }

        switch (sqlType) {

            case Types.VARCHAR:
                if (!(value instanceof String)) value = value.toString();
                break;

            case Types.BOOLEAN:
                if (value instanceof Boolean) {
                    ps.setBoolean(i, ((Boolean) value).booleanValue());
                    return;
                }
                break;

            case Types.TIMESTAMP:
                if (value instanceof java.util.Calendar) {
                    Calendar calValue = (Calendar) value;

// @todo: validate it is correct to comment out call to deprectated method
//                    if (cal == null) {
//                        /* NOTE: drivers are inconsistent in their handling of setTimestamp(i,date,cal)
//                         * so we won't use that, unless the user calls setCalendar().
//                         * I'm going with the theory that it makes sense to store
//                         * the time relative to the Calendar's timezone rather than
//                         * the system timezone otherwise, using a Calendar would be a no-op.
//                         */
//                        value = new java._sql.Timestamp(calValue.get(Calendar.YEAR) - 1900,
//                                                       calValue.get(Calendar.MONTH),
//                                                       calValue.get(Calendar.DATE),
//                                                       calValue.get(Calendar.HOUR_OF_DAY),
//                                                       calValue.get(Calendar.MINUTE),
//                                                       calValue.get(Calendar.SECOND),
//                                                       calValue.get(Calendar.MILLISECOND));
//                    } else {
                    value = new java.sql.Timestamp(calValue.getTimeInMillis());
//                    }
                } else if (java.util.Date.class.equals(value.getClass())) {
                    // some drivers don't like java.util.Date
                    value = new java.sql.Timestamp(((java.util.Date) value).getTime());
                }

                if (value instanceof java.sql.Timestamp) {
                    if (cal == null)
                        ps.setTimestamp(i, (java.sql.Timestamp) value);
                    else
                        ps.setTimestamp(i, (java.sql.Timestamp) value, cal);
                    return;
                }
                break;

            case Types.DATE:
                if (value instanceof java.util.Calendar) {
                    /* NOTE: see note above
                     Calendar cal = (Calendar)value;
                     value = new java._sql.Date(cal.getTimeInMillis());
                     ps.setDate(i, (java._sql.Date)value, cal);
                     return;
                     */
                    Calendar calValue = (Calendar) value;

                    // @todo: validate that commenting out deprected method is correct behavior
//                    if (cal == null) {
//                        value = new java._sql.Date(calValue.get(Calendar.YEAR - 1900),
//                                                  calValue.get(Calendar.MONTH),
//                                                  calValue.get(Calendar.DATE));
//                    } else {
                    value = new java.sql.Date(calValue.getTimeInMillis());
//                    }
                } else if (value.getClass() == java.util.Date.class) {
                    // some drivers don't like java.util.Date
                    value = new java.sql.Date(((java.util.Date) value).getTime());
                }

                if (value instanceof java.sql.Date) {
                    if (cal == null) {
                        ps.setDate(i, (java.sql.Date) value);
                    } else {
                        ps.setDate(i, (java.sql.Date) value, cal);
                    }
                    return;
                }
                break;

            case Types.TIME:
                if (value instanceof java.sql.Time) {
                    if (cal == null) {
                        ps.setTime(i, (java.sql.Time) value);
                    } else {
                        ps.setTime(i, (java.sql.Time) value, cal);
                    }
                    return;
                }
                break;
        }

        if (sqlType == Types.NULL) {
            ps.setObject(i, value);
        } else {
            ps.setObject(i, value, sqlType);
        }
    }

    /**
     * Determine if this SQL will generate a callable or prepared statement.
     *
     * @param args The method's argument list which this SQL annotation was assocatied with.
     * @return true if this statement will generated a CallableStatement
     */
    private boolean setCallableStatement(Object[] args) {

        // CallableStatement vs. PreparedStatement
        if (args != null && args.length == 1 && args[0] != null) {
            Class argClass = args[0].getClass();
            if (argClass.isArray() && JdbcControl.SQLParameter.class.isAssignableFrom(argClass.getComponentType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Build a prepared statement for a batch update.
     *
     * @param ps   The PreparedStatement object.
     * @param args
     * @param cal  A Calendar instance used to resolve date/time values.
     * @throws SQLException If a batch update cannot be performed.
     */
    private void doBatchUpdate(PreparedStatement ps, Object[] args, Calendar cal) throws SQLException {

        final int updateCount = ((Object[]) args[0]).length;
        final int[] sqlTypes = new int[args.length];
        final Object[] objArrays = new Object[args.length];

        // build an array of type values
        for (int i = 0; i < args.length; i++) {
            sqlTypes[i] = _tmf.getSqlType(args[i].getClass().getComponentType());
            objArrays[i] = TypeMappingsFactory.toObjectArray(args[i]);
        }

        for (int i = 0; i < updateCount; i++) {
            for (int j = 0; j < args.length; j++) {
                setPreparedStatementParameter(ps, j + 1, ((Object[]) objArrays[j])[i], sqlTypes[j], cal);
            }
            ps.addBatch();
        }
    }

    /**
     * Load element values from the SQL annotation which apply to Statements.
     *
     * @param context ControlBeanContext instance.
     * @param method  Annotated method.
     */
    private void loadSQLAnnotationStatmentOptions(ControlBeanContext context, Method method) {

        final JdbcControl.SQL methodSQL = (JdbcControl.SQL) context.getMethodPropertySet(method, JdbcControl.SQL.class);

        _batchUpdate = methodSQL.batchUpdate();
        _getGeneratedKeys = methodSQL.getGeneratedKeys();
        _genKeyColumnNames = methodSQL.generatedKeyColumnNames();
        _genKeyColumnIndexes = methodSQL.generatedKeyColumnIndexes();
        _scrollType = methodSQL.scrollableResultSet();
        _fetchDirection = methodSQL.fetchDirection();
        _fetchSize = methodSQL.fetchSize();
        _holdability = methodSQL.resultSetHoldabilityOverride();
        _maxRows = methodSQL.maxRows();
        _maxArray = methodSQL.arrayMaxLength();
    }

    /**
     * Checks that all statement options specified in annotation are supported by the database.
     *
     * @param metaData
     * @throws ControlException
     */
    private void checkJdbcSupport(DatabaseMetaData metaData) throws ControlException, SQLException {

        if (_getGeneratedKeys && !metaData.supportsGetGeneratedKeys()) {
            throw new ControlException("The database does not support getGeneratedKeys.");
        }

        if (_batchUpdate && !metaData.supportsBatchUpdates()) {
            throw new ControlException("The database does not support batchUpdates.");
        }

        if (!metaData.supportsResultSetConcurrency(_scrollType.getType(), _scrollType.getConcurrencyType())) {
            throw new ControlException("The database does not support the ResultSet concurrecy type: " + _scrollType.toString());
        }

        if (!metaData.supportsResultSetHoldability(_holdability.getHoldability())) {
            throw new ControlException("The database does not support the ResultSet holdability type: " + _holdability.toString());
        }
    }

    /**
     * The much maligned method for computing the maximum number of ResultSet rows this statement should return.
     *
     * @param method The annotated method.
     * @return max number of resultSet rows to return from the query.
     */
    private int computeMaxRows(Method method) {

        Class returnType = method.getReturnType();

        final boolean isArray = returnType.isArray();
        final boolean isRowSet = returnType.equals(RowSet.class);

        int maxSet = _maxRows;
        if (isArray && _maxArray != -1) {
            maxSet = _maxRows == -1 ? _maxArray + 1 : Math.min(_maxArray + 1, _maxRows);
        } else if (isRowSet && _maxRows > 0) {
            maxSet = _maxRows + 1;
        }

        return (maxSet == -1) ? 0 : maxSet;
    }
}
