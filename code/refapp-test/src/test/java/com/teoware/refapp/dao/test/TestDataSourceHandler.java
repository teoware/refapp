package com.teoware.refapp.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDataSourceHandler {

	public static Connection createDataSourceConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.apache.derby.jdbc.ClientDataSource");
		String url = "jdbc:derby://localhost/refapp";
		Connection connection = DriverManager.getConnection(url, "refapp", "abcd1234");
		connection.setAutoCommit(false);
		return connection;
	}
}
