package com.teoware.refapp.dao.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import bitronix.tm.resource.jdbc.PoolingDataSource;

public class ConnectionHandler {

	public static Connection createConnection(DataSource dataSource) throws SQLException {
		if (dataSource == null) {
			return createConnectionForTest();
		} else {
			return dataSource.getConnection();
		}
	}

	public static void closeConnection(Connection connection, boolean persistConnection) throws SQLException {
		if (!persistConnection && connection != null) {
			connection.close();
		}
	}

	private static Connection createConnectionForTest() throws SQLException {
		PoolingDataSource dataSource = createDataSourceForTest();
		return dataSource.getConnection();
	}

	private static PoolingDataSource createDataSourceForTest() {
		PoolingDataSource dataSource = new PoolingDataSource();
		dataSource.setClassName("org.apache.derby.jdbc.ClientXADataSource");
		dataSource.setUniqueName("refappDataSource");
		dataSource.setMaxPoolSize(5);
		dataSource.setAllowLocalTransactions(true);
		dataSource.setAutomaticEnlistingEnabled(true);
		dataSource.setTestQuery("SELECT 1 FROM SYS.SYSSCHEMAS");
		dataSource.getDriverProperties().setProperty("User", "refapp");
		dataSource.getDriverProperties().setProperty("Password", "abcd1234");
		dataSource.getDriverProperties().setProperty("ServerName", "localhost");
		dataSource.getDriverProperties().setProperty("PortNumber", "1527");
		dataSource.getDriverProperties().setProperty("DatabaseName", "refapp");
		dataSource.init();
		return dataSource;
	}
}
