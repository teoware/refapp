package com.teoware.refapp.glassfish.security;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SecurityStore {

    private final static Logger LOGGER = Logger.getLogger(SecurityStore.class.getName());

    private DataSource dataSource;

    public SecurityStore(String dataSourceName) {
        dataSource = lookupDataSource(dataSourceName);
    }

    public void addUser(String username, String salt, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = createConnection();
            statement = connection.prepareStatement(SQL.ADD_USER);
            statement.setString(1, username);
            statement.setString(2, salt);
            statement.setString(3, password);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Create User failed", e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Ignore
            }
        }
    }

    public String getSalt(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        String salt = null;
        try {
            connection = createConnection();
            statement = connection.prepareStatement(SQL.SALT_FOR_USER);
            statement.setString(1, username);
            result = statement.executeQuery();
            if (result.next()) {
                salt = result.getString(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "User not found", e);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Ignore
            }
        }
        return salt;
    }

    public boolean validateUser(String name, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        try {
            connection = createConnection();
            statement = connection.prepareStatement(SQL.VERIFY_USER);
            statement.setString(1, name);
            statement.setString(2, password);
            result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "User validation failed", e);
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // Ignore
            }
        }
        return false;
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error getting connection", e);
        }
        return connection;
    }

    private DataSource lookupDataSource(String dataSourceName) {
        Context context = null;
        try {
            context = new InitialContext();
            return (javax.sql.DataSource) context.lookup(dataSourceName);
        } catch (NamingException e) {
            LOGGER.log(Level.SEVERE, "Error getting context", e);
        } finally {
            try {
                if (context != null) {
                    context.close();
                }
            } catch (NamingException e) {
                LOGGER.log(Level.SEVERE, "Error closing context", e);
            }
        }
        return null;
    }
}
