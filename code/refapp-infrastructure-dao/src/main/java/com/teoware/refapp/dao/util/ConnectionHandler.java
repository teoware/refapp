package com.teoware.refapp.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public final class ConnectionHandler {

	public static Connection createConnection(DataSource dataSource) throws SQLException {
		return dataSource.getConnection();
	}

	public static void closeConnection(Connection connection, boolean persistConnection) throws SQLException {
		if (!persistConnection && connection != null) {
			connection.close();
		}
	}
}
