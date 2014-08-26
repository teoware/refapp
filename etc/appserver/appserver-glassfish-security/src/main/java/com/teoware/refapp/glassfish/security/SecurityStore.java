package com.teoware.refapp.glassfish.security;

import com.teoware.refapp.glassfish.security.domain.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SecurityStore {

    private final static Logger LOGGER = Logger.getLogger(SecurityStore.class.getName());
    private final static String AUTH_USER_V_SQL = "SELECT * FROM auth_users_v WHERE username = ?;";
    private final static String AUTH_GROUPS_V_SQL = "SELECT groupname FROM auth_groups_v WHERE username = ?;";
    private final static String GROUPS_SQL = "SELECT groupname FROM groups;";

    private DataSource dataSource;

    public SecurityStore(String dataSourceName) {
        dataSource = lookupDataSource(dataSourceName);
    }

    public User getUser(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        User user = null;
        try {
            connection = createConnection();
            statement = connection.prepareStatement(AUTH_USER_V_SQL);
            statement.setString(1, username);
            result = statement.executeQuery();
            if (result.next()) {
                user = new User();
                user.setUsername(result.getString(1));
                user.setPassword(result.getString(2));
                user.setSalt(result.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "User not found", e);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
        return user;
    }

    public List<String> getUserGroups(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<String> groups = new ArrayList<String>();
        try {
            connection = createConnection();
            statement = connection.prepareStatement(AUTH_GROUPS_V_SQL);
            statement.setString(1, username);
            result = statement.executeQuery();
            if (result.next()) {
                groups.add(result.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "User validation failed", e);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
        return groups;
    }

    public List<String> getAllGroups() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<String> groups = new ArrayList<String>();
        try {
            connection = createConnection();
            statement = connection.prepareStatement(GROUPS_SQL);
            result = statement.executeQuery();
            if (result.next()) {
                groups.add(result.getString(1));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "User validation failed", e);
        } finally {
            close(result);
            close(statement);
            close(connection);
        }
        return groups;
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

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Ignore
            }
        }
    }

    private void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                // Ignore
            }
        }
    }

    private void close(ResultSet result) {
        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                // Ignore
            }
        }
    }
}
