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
		User john = TestDataFactory.createUserJohn();
		resultSet.addRow(john.getUsername().getUsername(), john.getUserDetails().getFirstName(), john.getUserDetails()
				.getLastName(), DateTimeConverter.toSqlDate(john.getUserDetails().getBirthDate()), john
				.getUserDetails().getGender().toString(), john.getUserDetails().getEmail(), john.getUserDetails()
				.getPhone(), john.getUserAddress().getAddress(), john.getUserAddress().getPostalCode(), john
				.getUserAddress().getMunicipality(), john.getUserAddress().getRegion(), john.getUserAddress()
				.getCountry(), john.getUserStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(john
				.getUserStatus().getCreated()), DateTimeConverter.toSqlTimestamp(john.getUserStatus().getModified()));
	}

	private static void populateUsersViewRowJane(SimpleResultSet resultSet) {
		User jane = TestDataFactory.createUserJane();
		resultSet.addRow(jane.getUsername().getUsername(), jane.getUserDetails().getFirstName(), jane.getUserDetails()
				.getLastName(), DateTimeConverter.toSqlDate(jane.getUserDetails().getBirthDate()), jane
				.getUserDetails().getGender().toString(), jane.getUserDetails().getEmail(), jane.getUserDetails()
				.getPhone(), jane.getUserAddress().getAddress(), jane.getUserAddress().getPostalCode(), jane
				.getUserAddress().getMunicipality(), jane.getUserAddress().getRegion(), jane.getUserAddress()
				.getCountry(), jane.getUserStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(jane
				.getUserStatus().getCreated()), DateTimeConverter.toSqlTimestamp(jane.getUserStatus().getModified()));
	}

	private static void populateUsersViewRowJonah(SimpleResultSet resultSet) {
		User jonah = TestDataFactory.createUserJonah();
		resultSet.addRow(jonah.getUsername().getUsername(), jonah.getUserDetails().getFirstName(), jonah
				.getUserDetails().getLastName(), DateTimeConverter.toSqlDate(jonah.getUserDetails().getBirthDate()),
				jonah.getUserDetails().getGender().toString(), jonah.getUserDetails().getEmail(), jonah
						.getUserDetails().getPhone(), jonah.getUserAddress().getAddress(), jonah.getUserAddress()
						.getPostalCode(), jonah.getUserAddress().getMunicipality(), jonah.getUserAddress().getRegion(),
				jonah.getUserAddress().getCountry(), jonah.getUserStatus().getStatus().toString(), DateTimeConverter
						.toSqlTimestamp(jonah.getUserStatus().getCreated()), DateTimeConverter.toSqlTimestamp(jonah
						.getUserStatus().getModified()));
	}

	private static void populateUsersViewRowsAll(SimpleResultSet resultSet) {
		populateUsersViewRowJohn(resultSet);
		populateUsersViewRowJane(resultSet);
		populateUsersViewRowJonah(resultSet);
	}

	public static ResultSet createReadNote1ResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateNotesViewColumns(resultSet);
		populateNotesViewRow1(resultSet);
		return resultSet;
	}

	private static void populateNotesViewColumns(SimpleResultSet resultSet) {
		resultSet.addColumn(NoteTables.TITLE_COLUMN_NAME, Types.VARCHAR, 100, 0);
		resultSet.addColumn(NoteTables.DESCRIPTION_COLUMN_NAME, Types.VARCHAR, 300, 0);
		resultSet.addColumn(NoteTables.STATUS_COLUMN_NAME, Types.VARCHAR, 10, 0);
		resultSet.addColumn(NoteTables.CREATED_COLUMN_NAME, Types.DATE, 0, 0);
		resultSet.addColumn(NoteTables.MODIFIED_COLUMN_NAME, Types.DATE, 0, 0);
	}

	private static void populateNotesViewRow1(SimpleResultSet resultSet) {
		Note note = TestDataFactory.createNote1();
		resultSet.addRow(note.getTitle().getTitle(), note.getNoteDetails().getDescription(), note.getNoteStatus()
				.getStatus().toString(), DateTimeConverter.toSqlTimestamp(note.getNoteStatus().getCreated()),
				DateTimeConverter.toSqlTimestamp(note.getNoteStatus().getModified()));
	}
	
	public static ResultSet createReadTask1ResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateTasksViewColumns(resultSet);
		populateTasksViewRow1(resultSet);
		return resultSet;
	}

	private static void populateTasksViewColumns(SimpleResultSet resultSet) {
		resultSet.addColumn(TaskTables.TITLE_COLUMN_NAME, Types.VARCHAR, 100, 0);
		resultSet.addColumn(TaskTables.DESCRIPTION_COLUMN_NAME, Types.VARCHAR, 300, 0);
		resultSet.addColumn(TaskTables.STATUS_COLUMN_NAME, Types.VARCHAR, 10, 0);
		resultSet.addColumn(TaskTables.CREATED_COLUMN_NAME, Types.DATE, 0, 0);
		resultSet.addColumn(TaskTables.MODIFIED_COLUMN_NAME, Types.DATE, 0, 0);
	}

	private static void populateTasksViewRow1(SimpleResultSet resultSet) {
		Task task = TestDataFactory.createTask1();
		resultSet.addRow(task.getTitle().getTitle(), task.getTaskDetails().getDescription(), task.getTaskStatus()
				.getStatus().toString(), DateTimeConverter.toSqlTimestamp(task.getTaskStatus().getCreated()),
				DateTimeConverter.toSqlTimestamp(task.getTaskStatus().getModified()));
	}
}
