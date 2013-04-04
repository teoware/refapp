package com.teoware.refapp.dao.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
		if (!schemaExists(connection)) {
			return initializeDatabase(connection);
		} else {
			return connection;
		}
	}

	public static Connection initializeDatabase(Connection connection) throws SQLException {
		createDatabaseStructure(connection);
		insertDatabaseReferenceData(connection);
		return connection;
	}

	public static Connection createDatabaseStructure(Connection connection) throws SQLException {
		connection.prepareStatement(TestDataSourceMetaData.CREATE_SCHEMA_REFAPP_STATEMENT).execute();
		createRefTablesObjects(connection);
		createUserTablesObjects(connection);
		createNoteTablesObjects(connection);
		return connection;
	}

	private static Connection createRefTablesObjects(Connection connection) throws SQLException {
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_REF_GENDER_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_REF_USER_STATUS_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_REF_NOTE_STATUS_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_REF_TASK_STATUS_STATEMENT).execute();
		return connection;
	}

	private static Connection createUserTablesObjects(Connection connection) throws SQLException {
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_USERS_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_USER_STATUS_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_USER_DETAILS_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_USER_PASSWORD_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_USER_ADDRESS_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_VIEW_USERS_V_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TRIGGER_USERS_TRG1_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TRIGGER_USERS_TRG2_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TRIGGER_USER_DETAILS_TRG1_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TRIGGER_USER_PASSWORD_TRG1_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TRIGGER_USER_ADDRESS_TRG1_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TRIGGER_USER_STATUS_TRG1_STATEMENT).execute();
		return connection;
	}

	private static Connection createNoteTablesObjects(Connection connection) throws SQLException {
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_NOTES_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_NOTE_STATUS_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TABLE_NOTE_DETAILS_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_VIEW_NOTES_V_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TRIGGER_NOTES_TRG1_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TRIGGER_NOTES_TRG2_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TRIGGER_NOTE_DETAILS_TRG1_STATEMENT).execute();
		connection.prepareStatement(TestDataSourceMetaData.CREATE_TRIGGER_NOTE_STATUS_TRG1_STATEMENT).execute();
		return connection;
	}

	public static Connection insertDatabaseReferenceData(Connection connection) throws SQLException {
		connection.prepareStatement(TestDataSourceMetaData.INSERT_REF_USER_STATUS_STATEMENT_1).executeUpdate();
		connection.prepareStatement(TestDataSourceMetaData.INSERT_REF_USER_STATUS_STATEMENT_2).executeUpdate();
		connection.prepareStatement(TestDataSourceMetaData.INSERT_REF_USER_STATUS_STATEMENT_3).executeUpdate();
		connection.prepareStatement(TestDataSourceMetaData.INSERT_REF_USER_STATUS_STATEMENT_4).executeUpdate();
		connection.prepareStatement(TestDataSourceMetaData.INSERT_REF_NOTE_STATUS_STATEMENT).executeUpdate();
		connection.prepareStatement(TestDataSourceMetaData.INSERT_REF_GENDER_STATEMENT_1).executeUpdate();
		connection.prepareStatement(TestDataSourceMetaData.INSERT_REF_GENDER_STATEMENT_2).executeUpdate();
		return connection;
	}

	private static boolean schemaExists(Connection connection) throws SQLException {
		ResultSet result = connection.prepareStatement(TestDataSourceMetaData.SELECT_SCHEMA_REFAPP_STATEMENT)
				.executeQuery();
		return result.next();
	}
}
