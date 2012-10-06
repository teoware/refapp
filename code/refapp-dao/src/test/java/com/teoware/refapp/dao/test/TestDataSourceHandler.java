package com.teoware.refapp.dao.test;

import java.io.IOException;

import bitronix.tm.resource.jdbc.PoolingDataSource;

import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.util.PropertiesFile;

public class TestDataSourceHandler {

	public static PoolingDataSource createDataSourceForTest() throws DaoException {
		PropertiesFile properties = null;
		try {
			properties = getDataSourceProperties();
		} catch (IOException e) {
			throw new DaoException("Creation of test data source failed.", e);
		}

		PoolingDataSource dataSource = new PoolingDataSource();
		dataSource.setClassName(properties.get("className"));
		dataSource.setUniqueName(properties.get("uniqueName"));
		dataSource.setMaxPoolSize(properties.getInt("maxPoolSize"));
		dataSource.setAllowLocalTransactions(properties.getBoolean("allowLocalTransactions"));
		dataSource.setAutomaticEnlistingEnabled(properties.getBoolean("automaticEnlistingEnabled"));
		dataSource.setTestQuery(properties.get("testQuery"));
		dataSource.getDriverProperties().setProperty("User", properties.get("user"));
		dataSource.getDriverProperties().setProperty("Password", properties.get("password"));
		dataSource.getDriverProperties().setProperty("ServerName", properties.get("serverName"));
		dataSource.getDriverProperties().setProperty("PortNumber", properties.get("portNumber"));
		dataSource.getDriverProperties().setProperty("DatabaseName", properties.get("databaseName"));
		dataSource.init();
		return dataSource;
	}

	private static PropertiesFile getDataSourceProperties() throws IOException {
		return PropertiesFile.createFromClasspath("/properties/testdatasource.properties");
	}
}
