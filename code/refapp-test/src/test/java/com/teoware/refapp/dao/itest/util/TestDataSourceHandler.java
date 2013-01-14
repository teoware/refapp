package com.teoware.refapp.dao.itest.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.teoware.refapp.util.PropertiesFile;

public class TestDataSourceHandler {

	private static PropertiesFile props;
	private static String jdbcDriver;
	private static String url;
	private static String user;
	private static String pwd;

	public static Connection createDataSourceConnection() throws ClassNotFoundException, SQLException,
			FileNotFoundException, IOException {
		loadProperties();
		Class.forName(jdbcDriver);
		Connection connection = DriverManager.getConnection(url, user, pwd);
		connection.setAutoCommit(false);
		return connection;
	}

	public static void loadProperties() throws FileNotFoundException, IOException {
		props = PropertiesFile.createFromClasspath("/properties/testdatasource.properties");
		jdbcDriver = props.get("jdbcDriverClass");
		url = props.get("jdbcString");
		user = props.get("user");
		pwd = props.get("pwd");
	}
}
