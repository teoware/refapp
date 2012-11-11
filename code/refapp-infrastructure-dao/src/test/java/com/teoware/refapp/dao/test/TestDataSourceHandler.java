package com.teoware.refapp.dao.test;

import static com.teoware.refapp.dao.test.TestDataSourceMetaData.CREATE_SCHEMA_REFAPP_STATEMENT;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.CREATE_TABLE_AUTHORS_ADDRESS_STATEMENT;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.CREATE_TABLE_AUTHORS_PASSWORD_STATEMENT;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.CREATE_TABLE_AUTHORS_STATEMENT;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.CREATE_TABLE_AUTHORS_STATUS_STATEMENT;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.CREATE_TABLE_REF_AUTHORS_STATUS_STATEMENT;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.CREATE_TABLE_REF_GENDER_STATEMENT;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.CREATE_TRIGGER_AUTHORS_TRG01_STATEMENT;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.CREATE_TRIGGER_AUTHORS_TRG02_STATEMENT;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.CREATE_VIEW_AUTHORS_V_STATEMENT;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.INSERT_REF_AUTHORS_STATUS_STATEMENT_1;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.INSERT_REF_AUTHORS_STATUS_STATEMENT_2;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.INSERT_REF_AUTHORS_STATUS_STATEMENT_3;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.INSERT_REF_AUTHORS_STATUS_STATEMENT_4;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.INSERT_REF_GENDER_STATEMENT_1;
import static com.teoware.refapp.dao.test.TestDataSourceMetaData.INSERT_REF_GENDER_STATEMENT_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class TestDataSourceHandler {

	public static Connection createDataSourceConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.hsqldb.jdbcDriver");
		Connection connection = DriverManager.getConnection("jdbc:hsqldb:mem:refapp", "sa", "");
		connection.setAutoCommit(false);
		return connection;
	}

	public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
		Connection connection = createDataSourceConnection();
		return initializeDatabase(connection);
	}

	public static Connection initializeDatabase(Connection connection) throws SQLException {
		createDatabaseStructure(connection);
		insertDatabaseReferenceData(connection);
		return connection;
	}

	public static Connection createDatabaseStructure(Connection connection) throws SQLException {
		connection.prepareStatement(CREATE_SCHEMA_REFAPP_STATEMENT).execute();
		connection.prepareStatement(CREATE_TABLE_REF_GENDER_STATEMENT).execute();
		connection.prepareStatement(CREATE_TABLE_REF_AUTHORS_STATUS_STATEMENT).execute();
		connection.prepareStatement(CREATE_TABLE_AUTHORS_STATEMENT).execute();
		connection.prepareStatement(CREATE_TABLE_AUTHORS_STATUS_STATEMENT).execute();
		connection.prepareStatement(CREATE_TABLE_AUTHORS_PASSWORD_STATEMENT).execute();
		connection.prepareStatement(CREATE_TABLE_AUTHORS_ADDRESS_STATEMENT).execute();
		connection.prepareStatement(CREATE_VIEW_AUTHORS_V_STATEMENT).execute();
		connection.prepareStatement(CREATE_TRIGGER_AUTHORS_TRG01_STATEMENT).execute();
		connection.prepareStatement(CREATE_TRIGGER_AUTHORS_TRG02_STATEMENT).execute();
		return connection;
	}

	public static Connection insertDatabaseReferenceData(Connection connection) throws SQLException {
		connection.prepareStatement(INSERT_REF_AUTHORS_STATUS_STATEMENT_1).executeUpdate();
		connection.prepareStatement(INSERT_REF_AUTHORS_STATUS_STATEMENT_2).executeUpdate();
		connection.prepareStatement(INSERT_REF_AUTHORS_STATUS_STATEMENT_3).executeUpdate();
		connection.prepareStatement(INSERT_REF_AUTHORS_STATUS_STATEMENT_4).executeUpdate();
		connection.prepareStatement(INSERT_REF_GENDER_STATEMENT_1).executeUpdate();
		connection.prepareStatement(INSERT_REF_GENDER_STATEMENT_2).executeUpdate();
		return connection;
	}
}
