package by.mnk.htp.glotovs.msr.connection.pool;


import  by.mnk.htp.glotovs.msr.connection.pool.exception.ConnectionPoolException;
import by.mnk.htp.glotovs.msr.connection.pool.util.DBParameter;
import by.mnk.htp.glotovs.msr.connection.pool.util.DBResourceManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

/**
 * Created by Sefire on 25.10.2016.
 */

public class ConnectionPool {

    private static Logger log = Logger.getLogger(ConnectionPool.class);
    private static final String SQL_ERROR_MESSAGE = "SQL exception occurred.";
    private static final String RESULT_SET_ERROR_MESSAGE = "Unable to close result set.";
    private static final String STATEMENT_ERROR_MESSAGE = "Unable to close statement.";
    private static final String CONNECTION_ERROR_MESSAGE = "Unable to return connection to the pool.";
    private static final String CONNECTION_POOL_ERROR_MESSAGE = "Unable to close connection pool.";
    private static final String SQL_CONNECTION_POOL_ERROR_MESSAGE = "SQLException in connection pool.";
    private static final String DRIVER_CLASS_ERROR_MESSAGE = "Can't find database driver class.";
    private static final String DATA_SOURCE_ERROR_MESSAGE = "Error connecting to the data source.";
    private static final String CLOSE_CONNECTION_ERROR_MESSAGE = "Attempting to close closed connection.";
    private static final String GIVEN_AWAY_CONNECTION_ERROR_MESSAGE = "Error deleting connection from the given away connection pool.";
    private static final String ALLOCATING_CONNECTION_ERROR_MESSAGE = "Error allocating connection in the pool.";

    private static final ConnectionPool instance = new ConnectionPool();

    BlockingQueue<Connection> connectionQueue;
    BlockingQueue<Connection> givenAwayConQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.user = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);

        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POLL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }

    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public void initPoolData() throws ConnectionPoolException {

        try {
            //Class.forName(driverName);
            Class.forName("com.mysql.jdbc.Driver");
            givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
            connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
            for(int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                PooledConnection pooledConnection = new PooledConnection(connection);

                connectionQueue.add(pooledConnection);
            }
            log.info("initPoolData in class ConnectionPool is good");
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException(SQL_CONNECTION_POOL_ERROR_MESSAGE, e);
        } catch (SQLException e) {
            throw new ConnectionPoolException(DRIVER_CLASS_ERROR_MESSAGE, e);
        }
    }

    public void dispose() throws ConnectionPoolException {
        clearConnectionQueue();
    }

    private void clearConnectionQueue() throws ConnectionPoolException {
        try{
            closeConnectionQueue(givenAwayConQueue);
            closeConnectionQueue(connectionQueue);
        } catch (SQLException e) {
            throw new ConnectionPoolException(CONNECTION_POOL_ERROR_MESSAGE, e);
        }
    }

    public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(CONNECTION_ERROR_MESSAGE, e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(RESULT_SET_ERROR_MESSAGE, e);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(STATEMENT_ERROR_MESSAGE, e);
        }
    }

    public void closeConnection(Connection con, Statement st) throws ConnectionPoolException {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(RESULT_SET_ERROR_MESSAGE, e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(STATEMENT_ERROR_MESSAGE, e);
        }
    }

    private void closeConnectionQueue(BlockingQueue<Connection> connectionQueue) throws SQLException {
        Connection connection;
        while ((connection = connectionQueue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            ((PooledConnection) connection).reallyClose();
        }
    }

    public Connection takeConnection() throws ConnectionPoolException {
        Connection connection = null;

        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);

        } catch (InterruptedException e) {
            throw new ConnectionPoolException(DATA_SOURCE_ERROR_MESSAGE, e);
        }

        return connection;
    }

    private class PooledConnection implements Connection {

        private Connection connection;

        public PooledConnection(Connection connection) throws SQLException {
            this.connection = connection;
            this.connection.setAutoCommit(true);
        }

        public void reallyClose() throws SQLException {
            connection.close();
        }


        public Statement createStatement() throws SQLException {
            return connection.createStatement();
        }

        public PreparedStatement prepareStatement(String sql) throws SQLException {
            return connection.prepareStatement(sql);
        }


        public CallableStatement prepareCall(String sql) throws SQLException {
            return connection.prepareCall(sql);
        }

        public String nativeSQL(String sql) throws SQLException {
            return connection.nativeSQL(sql);
        }

        public void setAutoCommit(boolean autoCommit) throws SQLException {
            connection.setAutoCommit(autoCommit);
        }

        public boolean getAutoCommit() throws SQLException {
            return connection.getAutoCommit();
        }

        public void commit() throws SQLException {
            connection.commit();
        }

        public void rollback() throws SQLException {
            connection.rollback();
        }

        public void close() throws SQLException {
            if (connection.isClosed()) {
                throw new SQLException(CLOSE_CONNECTION_ERROR_MESSAGE);
            }

            if (connection.isReadOnly()) {
                connection.setReadOnly(false);
            }

            if (!givenAwayConQueue.remove(this)) {
                throw new SQLException(GIVEN_AWAY_CONNECTION_ERROR_MESSAGE);
            }

            if (!connectionQueue.offer(this)) {
                throw new SQLException(ALLOCATING_CONNECTION_ERROR_MESSAGE);
            }
        }

        public boolean isClosed() throws SQLException {
            return connection.isClosed();
        }

        public DatabaseMetaData getMetaData() throws SQLException {
            return connection.getMetaData();
        }

        public void setReadOnly(boolean readOnly) throws SQLException {
            connection.setReadOnly(readOnly);
        }

        public boolean isReadOnly() throws SQLException {
            return connection.isReadOnly();
        }

        public void setCatalog(String catalog) throws SQLException {
            connection.setCatalog(catalog);
        }

        public String getCatalog() throws SQLException {
            return connection.getCatalog();
        }

        public void setTransactionIsolation(int level) throws SQLException {
            connection.setTransactionIsolation(level);
        }

        public int getTransactionIsolation() throws SQLException {
            return connection.getTransactionIsolation();
        }

        public SQLWarning getWarnings() throws SQLException {
            return connection.getWarnings();
        }

        public void clearWarnings() throws SQLException {
            connection.clearWarnings();
        }

        public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        }

        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
            return prepareCall(sql, resultSetType, resultSetConcurrency);
        }

        public Map<String, Class<?>> getTypeMap() throws SQLException {
            return connection.getTypeMap();
        }

        public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
            connection.setTypeMap(map);
        }

        public void setHoldability(int holdability) throws SQLException {
            connection.setHoldability(holdability);
        }

        public int getHoldability() throws SQLException {
            return connection.getHoldability();
        }

        public Savepoint setSavepoint() throws SQLException {
            return connection.setSavepoint();
        }

        public Savepoint setSavepoint(String name) throws SQLException {
            return connection.setSavepoint(name);
        }

        public void rollback(Savepoint savepoint) throws SQLException {
            connection.rollback(savepoint);
        }

        public void releaseSavepoint(Savepoint savepoint) throws SQLException {
            connection.releaseSavepoint(savepoint);
        }

        public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        }

        public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        }

        public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
            return connection.prepareStatement(sql, columnIndexes);
        }

        public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
            return connection.prepareStatement(sql, columnNames);
        }

        public Clob createClob() throws SQLException {
            return connection.createClob();
        }

        public Blob createBlob() throws SQLException {
            return connection.createBlob();
        }

        public NClob createNClob() throws SQLException {
            return connection.createNClob();
        }

        public SQLXML createSQLXML() throws SQLException {
            return connection.createSQLXML();
        }

        public boolean isValid(int timeout) throws SQLException {
            return connection.isValid(timeout);
        }

        public void setClientInfo(String name, String value) throws SQLClientInfoException {
            connection.setClientInfo(name, value);
        }

        public void setClientInfo(Properties properties) throws SQLClientInfoException {
            connection.setClientInfo(properties);
        }

        public String getClientInfo(String name) throws SQLException {
            return connection.getClientInfo(name);
        }

        public Properties getClientInfo() throws SQLException {
            return connection.getClientInfo();
        }

        public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
            return connection.createArrayOf(typeName, elements);
        }

        public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
            return connection.createStruct(typeName, attributes);
        }

        public void setSchema(String schema) throws SQLException {
            connection.setSchema(schema);
        }

        public String getSchema() throws SQLException {
            return connection.getSchema();
        }

        public void abort(Executor executor) throws SQLException {
            connection.abort(executor);
        }

        public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
            connection.setNetworkTimeout(executor, milliseconds);
        }

        public int getNetworkTimeout() throws SQLException {
            return connection.getNetworkTimeout();
        }

        public <T> T unwrap(Class<T> iface) throws SQLException {
            return connection.unwrap(iface);
        }

        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return connection.isWrapperFor(iface);
        }

    }

}
