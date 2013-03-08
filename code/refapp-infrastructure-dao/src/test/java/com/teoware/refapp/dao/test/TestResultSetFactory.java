package com.teoware.refapp.dao.test;

import java.sql.ResultSet;

import org.apache.derby.client.am.Types;
import org.h2.tools.SimpleResultSet;

import com.teoware.refapp.dao.metadata.NoteTables;
import com.teoware.refapp.dao.metadata.TaskTables;
import com.teoware.refapp.dao.metadata.UserTables;
import com.teoware.refapp.model.note.Note;
import com.teoware.refapp.model.task.Task;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.test.util.TestDataFactory;
import com.teoware.refapp.util.time.DateTimeConverter;

public final class TestResultSetFactory {

	public static ResultSet createReadAllUsersResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUsersViewColumns(resultSet);
		populateUsersViewRowsAll(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserJohnResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUsersViewColumns(resultSet);
		populateUsersViewRowJohn(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserJaneResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUsersViewColumns(resultSet);
		populateUsersViewRowJane(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserJonahResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUsersViewColumns(resultSet);
		populateUsersViewRowJonah(resultSet);
		return resultSet;
	}

	private static void populateUsersViewColumns(SimpleResultSet resultSet) {
		resultSet.addColumn(UserTables.USERNAME_COLUMN_NAME, Types.VARCHAR, 20, 0);
		resultSet.addColumn(UserTables.FIRSTNAME_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(UserTables.LASTNAME_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(UserTables.BIRTHDATE_COLUMN_NAME, Types.DATE, 0, 0);
		resultSet.addColumn(UserTables.GENDER_COLUMN_NAME, Types.VARCHAR, 10, 0);
		resultSet.addColumn(UserTables.EMAIL_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(UserTables.PHONE_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(UserTables.ADDRESS_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(UserTables.POSTALCODE_COLUMN_NAME, Types.VARCHAR, 10, 0);
		resultSet.addColumn(UserTables.MUNICIPALITY_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(UserTables.REGION_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(UserTables.COUNTRY_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(UserTables.STATUS_COLUMN_NAME, Types.VARCHAR, 10, 0);
		resultSet.addColumn(UserTables.CREATED_COLUMN_NAME, Types.DATE, 0, 0);
		resultSet.addColumn(UserTables.MODIFIED_COLUMN_NAME, Types.DATE, 0, 0);
	}

	private static void populateUsersViewRowJohn(SimpleResultSet resultSet) {
		User user = TestDataFactory.createUserJohn();
		resultSet.addRow(user.getUsername().getUsername(), user.getUserDetails().getFirstName(), user.getUserDetails()
				.getLastName(), DateTimeConverter.toSqlDate(user.getUserDetails().getBirthDate()), user
				.getUserDetails().getGender().toString(), user.getUserDetails().getEmail(), user.getUserDetails()
				.getPhone(), user.getUserAddress().getAddress(), user.getUserAddress().getPostalCode(), user
				.getUserAddress().getMunicipality(), user.getUserAddress().getRegion(), user.getUserAddress()
				.getCountry(), user.getUserStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(user
				.getUserStatus().getCreated()), DateTimeConverter.toSqlTimestamp(user.getUserStatus().getModified()));
	}

	private static void populateUsersViewRowJane(SimpleResultSet resultSet) {
		User user = TestDataFactory.createUserJane();
		resultSet.addRow(user.getUsername().getUsername(), user.getUserDetails().getFirstName(), user.getUserDetails()
				.getLastName(), DateTimeConverter.toSqlDate(user.getUserDetails().getBirthDate()), user
				.getUserDetails().getGender().toString(), user.getUserDetails().getEmail(), user.getUserDetails()
				.getPhone(), user.getUserAddress().getAddress(), user.getUserAddress().getPostalCode(), user
				.getUserAddress().getMunicipality(), user.getUserAddress().getRegion(), user.getUserAddress()
				.getCountry(), user.getUserStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(user
				.getUserStatus().getCreated()), DateTimeConverter.toSqlTimestamp(user.getUserStatus().getModified()));
	}

	private static void populateUsersViewRowJonah(SimpleResultSet resultSet) {
		User user = TestDataFactory.createUserJonah();
		resultSet.addRow(user.getUsername().getUsername(), user.getUserDetails().getFirstName(), user.getUserDetails()
				.getLastName(), DateTimeConverter.toSqlDate(user.getUserDetails().getBirthDate()), user
				.getUserDetails().getGender().toString(), user.getUserDetails().getEmail(), user.getUserDetails()
				.getPhone(), user.getUserAddress().getAddress(), user.getUserAddress().getPostalCode(), user
				.getUserAddress().getMunicipality(), user.getUserAddress().getRegion(), user.getUserAddress()
				.getCountry(), user.getUserStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(user
				.getUserStatus().getCreated()), DateTimeConverter.toSqlTimestamp(user.getUserStatus().getModified()));
	}

	private static void populateUsersViewRowsAll(SimpleResultSet resultSet) {
		populateUsersViewRowJohn(resultSet);
		populateUsersViewRowJane(resultSet);
		populateUsersViewRowJonah(resultSet);
	}

	public static ResultSet createReadAllUserIdsResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUsersTableColumns(resultSet);
		populateUsersTableRowsAll(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserIdJohnResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUsersTableColumns(resultSet);
		populateUsersTableRowJohn(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserIdJaneResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUsersTableColumns(resultSet);
		populateUsersTableRowJane(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserIdJonahResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUsersTableColumns(resultSet);
		populateUsersTableRowJonah(resultSet);
		return resultSet;
	}

	private static void populateUsersTableColumns(SimpleResultSet resultSet) {
		resultSet.addColumn(UserTables.ID_COLUMN_NAME, Types.BIGINT, 0, 0);
		resultSet.addColumn(UserTables.USERNAME_COLUMN_NAME, Types.VARCHAR, 20, 0);
	}

	private static void populateUsersTableRowJohn(SimpleResultSet resultSet) {
		User user = TestDataFactory.createUserJohn();
		resultSet.addRow(1L, user.getUsername().getUsername());
	}

	private static void populateUsersTableRowJane(SimpleResultSet resultSet) {
		User user = TestDataFactory.createUserJane();
		resultSet.addRow(2L, user.getUsername().getUsername());
	}

	private static void populateUsersTableRowJonah(SimpleResultSet resultSet) {
		User user = TestDataFactory.createUserJonah();
		resultSet.addRow(3L, user.getUsername().getUsername());
	}

	private static void populateUsersTableRowsAll(SimpleResultSet resultSet) {
		populateUsersTableRowJohn(resultSet);
		populateUsersTableRowJane(resultSet);
		populateUsersTableRowJonah(resultSet);
	}

	public static ResultSet createReadAllUserPasswordsResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUserPasswordTableColumns(resultSet);
		populateUserPasswordTableRowsAll(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserPasswordJohnResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUserPasswordTableColumns(resultSet);
		populateUserPasswordTableRowJohn(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserPasswordJaneResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUserPasswordTableColumns(resultSet);
		populateUserPasswordTableRowJane(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserPasswordJonahResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUserPasswordTableColumns(resultSet);
		populateUserPasswordTableRowJonah(resultSet);
		return resultSet;
	}

	private static void populateUserPasswordTableColumns(SimpleResultSet resultSet) {
		resultSet.addColumn(UserTables.ID_COLUMN_NAME, Types.BIGINT, 0, 0);
		resultSet.addColumn(UserTables.USER_ID_COLUMN_NAME, Types.BIGINT, 0, 0);
		resultSet.addColumn(UserTables.PASSWORD_COLUMN_NAME, Types.VARCHAR, 100, 0);
		resultSet.addColumn(UserTables.SALT_COLUMN_NAME, Types.VARCHAR, 100, 0);
	}

	private static void populateUserPasswordTableRowJohn(SimpleResultSet resultSet) {
		UserPassword userPassword = TestDataFactory.createUserPasswordJohn();
		resultSet.addRow(1L, 1L, userPassword.getPassword(), userPassword.getSalt());
	}

	private static void populateUserPasswordTableRowJane(SimpleResultSet resultSet) {
		UserPassword userPassword = TestDataFactory.createUserPasswordJane();
		resultSet.addRow(1L, 1L, userPassword.getPassword(), userPassword.getSalt());
	}

	private static void populateUserPasswordTableRowJonah(SimpleResultSet resultSet) {
		UserPassword userPassword = TestDataFactory.createUserPasswordJonah();
		resultSet.addRow(1L, 1L, userPassword.getPassword(), userPassword.getSalt());
	}

	private static void populateUserPasswordTableRowsAll(SimpleResultSet resultSet) {
		populateUserPasswordTableRowJohn(resultSet);
		populateUserPasswordTableRowJane(resultSet);
		populateUserPasswordTableRowJonah(resultSet);
	}

	public static ResultSet createReadNote1ResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateNotesViewColumns(resultSet);
		populateNotesViewRow1(resultSet);
		return resultSet;
	}

	private static void populateNotesViewColumns(SimpleResultSet resultSet) {
		resultSet.addColumn(NoteTables.UUID_COLUMN_NAME, Types.VARCHAR, 36, 0);
		resultSet.addColumn(NoteTables.TITLE_COLUMN_NAME, Types.VARCHAR, 100, 0);
		resultSet.addColumn(NoteTables.DESCRIPTION_COLUMN_NAME, Types.VARCHAR, 300, 0);
		resultSet.addColumn(NoteTables.STATUS_COLUMN_NAME, Types.VARCHAR, 10, 0);
		resultSet.addColumn(NoteTables.CREATED_COLUMN_NAME, Types.DATE, 0, 0);
		resultSet.addColumn(NoteTables.MODIFIED_COLUMN_NAME, Types.DATE, 0, 0);
	}

	private static void populateNotesViewRow1(SimpleResultSet resultSet) {
		Note note = TestDataFactory.createNote1();
		resultSet.addRow(note.getUuid().getUuid(), note.getNoteDetails().getTitle(), note.getNoteDetails()
				.getDescription(), note.getNoteStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(note
				.getNoteStatus().getCreated()), DateTimeConverter.toSqlTimestamp(note.getNoteStatus().getModified()));
	}

	public static ResultSet createReadNoteId1ResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateNotesTableColumns(resultSet);
		populateNotesTableRow1(resultSet);
		return resultSet;
	}

	private static void populateNotesTableColumns(SimpleResultSet resultSet) {
		resultSet.addColumn(NoteTables.ID_COLUMN_NAME, Types.BIGINT, 0, 0);
		resultSet.addColumn(NoteTables.USER_ID_COLUMN_NAME, Types.BIGINT, 0, 0);
		resultSet.addColumn(NoteTables.UUID_COLUMN_NAME, Types.VARCHAR, 100, 0);
	}

	private static void populateNotesTableRow1(SimpleResultSet resultSet) {
		Note note = TestDataFactory.createNote1();
		resultSet.addRow(1L, 1L, note.getUuid().getUuid());
	}

	public static ResultSet createReadTask1ResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateTasksViewColumns(resultSet);
		populateTasksViewRow1(resultSet);
		return resultSet;
	}

	private static void populateTasksViewColumns(SimpleResultSet resultSet) {
		resultSet.addColumn(TaskTables.UUID_COLUMN_NAME, Types.VARCHAR, 36, 0);
		resultSet.addColumn(TaskTables.TITLE_COLUMN_NAME, Types.VARCHAR, 100, 0);
		resultSet.addColumn(TaskTables.DESCRIPTION_COLUMN_NAME, Types.VARCHAR, 300, 0);
		resultSet.addColumn(TaskTables.STATUS_COLUMN_NAME, Types.VARCHAR, 10, 0);
		resultSet.addColumn(TaskTables.CREATED_COLUMN_NAME, Types.DATE, 0, 0);
		resultSet.addColumn(TaskTables.MODIFIED_COLUMN_NAME, Types.DATE, 0, 0);
	}

	private static void populateTasksViewRow1(SimpleResultSet resultSet) {
		Task task = TestDataFactory.createTask1();
		resultSet.addRow(task.getUuid().getUuid(), task.getTaskDetails().getTitle(), task.getTaskDetails()
				.getDescription(), task.getTaskStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(task
				.getTaskStatus().getCreated()), DateTimeConverter.toSqlTimestamp(task.getTaskStatus().getModified()));
	}

	public static ResultSet createReadTaskId1ResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateTasksTableColumns(resultSet);
		populateTasksTableRow1(resultSet);
		return resultSet;
	}

	private static void populateTasksTableColumns(SimpleResultSet resultSet) {
		resultSet.addColumn(TaskTables.ID_COLUMN_NAME, Types.BIGINT, 0, 0);
		resultSet.addColumn(TaskTables.USER_ID_COLUMN_NAME, Types.BIGINT, 0, 0);
		resultSet.addColumn(TaskTables.UUID_COLUMN_NAME, Types.VARCHAR, 100, 0);
	}

	private static void populateTasksTableRow1(SimpleResultSet resultSet) {
		Task task = TestDataFactory.createTask1();
		resultSet.addRow(1L, 1L, task.getUuid().getUuid());
	}
}
