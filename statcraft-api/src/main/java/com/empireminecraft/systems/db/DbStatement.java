/*
 * Copyright (c) 2015. Starlis LLC / dba Empire Minecraft
 * https://empiredb.emc.gs/
 *
 * MIT License
 */

package com.empireminecraft.systems.db;

import com.demonwav.statcraft.StatCraft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Manages a connection to the database pool and lets you work with an active
 * prepared statement.
 * <p/>
 * Must close after you are done with it, preferably wrapping in a try/catch/finally
 * DbStatement statement = null;
 * try {
 * statement = new DbStatement();
 * // use it
 * } catch (Exception e) {
 * // handle exception
 * } finally {
 * if (statement != null) {
 * statement.close();
 * }
 * }
 */
@SuppressWarnings({"JavaDoc", "WeakerAccess", "unchecked", "unused"})
public class DbStatement implements AutoCloseable {
    Connection dbConn;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public ResultSetMetaData resultSetMetaData;
    public String[] resultCols;
    public String query = "";
    // Has changes been made to a transaction w/o commit/rollback on close
    public boolean isDirty = false;

    public DbStatement(Connection connection) throws SQLException {
        dbConn = connection;
    }

    /**
     * Starts a transaction on this connection
     *
     * @return
     * @throws SQLException
     */
    public void startTransaction() throws SQLException {
        dbConn.setAutoCommit(false);
    }

    /**
     * Commits a pending transaction on this connection
     *
     * @return
     * @throws SQLException
     */
    public void commit() throws SQLException {
        dbConn.commit();
        isDirty = false;
    }

    /**
     * Rollsback a pending transaction on this connection.
     *
     * @return
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        dbConn.rollback();
        isDirty = false;
    }

    /**
     * Initiates a new prepared statement on this connection.
     *
     * @param query
     * @throws SQLException
     */
    public DbStatement query(String query) throws SQLException {
        this.query = query;
        closeStatement();
        try {
            preparedStatement = dbConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            close();
            throw e;
        }

        return this;
    }

    /**
     * Utility method used by execute calls to set the statements parameters to execute on.
     *
     * @param params Array of Objects to use for each parameter.
     * @return
     * @throws SQLException
     */
    protected void prepareExecute(Object... params) throws SQLException {
        isDirty = true;
        closeResult();
        if (preparedStatement == null) {
            throw new IllegalStateException("Run Query first on statement before executing!");
        }

        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
    }

    /**
     * Execute an update query with the supplied parameters
     *
     * @param params
     * @return
     * @throws SQLException
     */
    public int executeUpdate(Object... params) throws SQLException {
        try {
            prepareExecute(params);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            close();
            throw e;
        }
    }

    /**
     * Executes the prepared statement with the supplied parameters.
     *
     * @param params
     * @return
     * @throws SQLException
     */
    public DbStatement execute(Object... params) throws SQLException {
        try {
            prepareExecute(params);
            resultSet = preparedStatement.executeQuery();
            resultSetMetaData = resultSet.getMetaData();
            int numberOfColumns = resultSetMetaData.getColumnCount();

            resultCols = new String[numberOfColumns];
            // get the column names; column indexes start from 1
            for (int i = 1; i < numberOfColumns + 1; i++) {
                resultCols[i - 1] = resultSetMetaData.getColumnLabel(i);
            }
        } catch (SQLException e) {
            close();
            throw e;
        }
        return this;
    }

    /**
     * Gets the Id of last insert
     *
     * @return Long
     */
    public Long getLastInsertId() throws SQLException {
        try (ResultSet genKeys = preparedStatement.getGeneratedKeys()) {
            if (genKeys == null) {
                return null;
            }
            Long result = null;
            if (genKeys.next()) {
                result = genKeys.getLong(1);
            }
            return result;
        }
    }

    /**
     * Gets all results as an array of DbRow
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<DbRow> getResults() throws SQLException {
        if (resultSet == null) {
            return null;
        }
        ArrayList<DbRow> result = new ArrayList<>();
        DbRow row;
        while ((row = getNextRow()) != null) {
            result.add(row);
        }
        return result;
    }

    /**
     * Gets the next DbRow from the result set.
     *
     * @return DbRow containing a hashmap of the columns
     * @throws SQLException
     */
    public DbRow getNextRow() throws SQLException {
        if (resultSet == null) {
            return null;
        }

        ResultSet nextResultSet = getNextResultSet();
        if (nextResultSet != null) {
            DbRow row = new DbRow();
            for (String col : resultCols) {
                row.put(col, nextResultSet.getObject(col));
            }
            return row;
        }
        return null;
    }

    public <T> T getFirstColumn() throws SQLException {
        ResultSet resultSet = getNextResultSet();
        if (resultSet != null) {
            return (T) resultSet.getObject(1);
        }
        return null;
    }

    /**
     * Util method to get the next result set and close it when done.
     *
     * @return
     * @throws SQLException
     */
    protected ResultSet getNextResultSet() throws SQLException {
        if (resultSet != null && resultSet.next()) {
            return resultSet;
        } else {
            closeResult();
            return null;
        }
    }
    private void closeResult() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
            resultSet = null;
        }
    }
    private void closeStatement() throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
            resultSet = null;
            preparedStatement = null;
        }
    }
    /**
     * Closes all resources associated with this statement and returns the connection to the pool.
     */
    public void close() {
        try {
            if (dbConn != null) {
                dbConn.close();
            }
            preparedStatement = null;
            resultSet = null;
            dbConn = null;
        } catch (SQLException ex) {
            StatCraft.getInstance().warn("Failed to close DB connection: " + query);
            ex.printStackTrace();
        }

    }

    public boolean isClosed() throws SQLException {
        return dbConn == null || dbConn.isClosed();
    }
}
