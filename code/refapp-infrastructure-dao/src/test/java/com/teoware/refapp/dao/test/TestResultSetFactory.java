package com.teoware.refapp.dao.test;

import static com.teoware.refapp.dao.metadata.UsersTable.ADDRESS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.BIRTHDATE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.COUNTRY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.CREATED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.EMAIL_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.FIRSTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.GENDER_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.LASTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.MODIFIED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.MUNICIPALITY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.PHONE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.POSTALCODE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.REGION_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.STATUS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTable.USERNAME_COLUMN_NAME;

import java.sql.ResultSet;

import org.apache.derby.client.am.Types;
import org.h2.tools.SimpleResultSet;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.test.util.TestDataFactory;
import com.teoware.refapp.util.time.DateTimeConverter;

public final class TestResultSetFactory {

	public static ResultSet createReadAllUsersResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUserViewColumns(resultSet);
		populateAllUsersViewRows(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserJohnResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUserViewColumns(resultSet);
		populateUserJohnViewRow(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserJaneResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUserViewColumns(resultSet);
		populateAllUsersViewRows(resultSet);
		return resultSet;
	}

	public static ResultSet createReadUserJonahResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUserViewColumns(resultSet);
		populateAllUsersViewRows(resultSet);
		return resultSet;
	}

	private static void populateUserViewColumns(SimpleResultSet resultSet) {
		resultSet.addColumn(USERNAME_COLUMN_NAME, Types.VARCHAR, 20, 0);
		resultSet.addColumn(FIRSTNAME_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(LASTNAME_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(BIRTHDATE_COLUMN_NAME, Types.DATE, 0, 0);
		resultSet.addColumn(GENDER_COLUMN_NAME, Types.VARCHAR, 10, 0);
		resultSet.addColumn(EMAIL_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(PHONE_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(ADDRESS_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(POSTALCODE_COLUMN_NAME, Types.VARCHAR, 10, 0);
		resultSet.addColumn(MUNICIPALITY_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(REGION_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(COUNTRY_COLUMN_NAME, Types.VARCHAR, 50, 0);
		resultSet.addColumn(STATUS_COLUMN_NAME, Types.VARCHAR, 10, 0);
		resultSet.addColumn(CREATED_COLUMN_NAME, Types.DATE, 0, 0);
		resultSet.addColumn(MODIFIED_COLUMN_NAME, Types.DATE, 0, 0);
	}

	private static void populateUserJohnViewRow(SimpleResultSet resultSet) {
		User john = TestDataFactory.createUserJohn();
		resultSet.addRow(john.getUsername().getUsername(), john.getUserInfo().getFirstName(), john.getUserInfo()
				.getLastName(), DateTimeConverter.toSqlDate(john.getUserInfo().getBirthDate()), john.getUserInfo()
				.getGender().toString(), john.getUserInfo().getEmail(), john.getUserInfo().getPhone(), john
				.getUserAddress().getAddress(), john.getUserAddress().getPostalCode(), john.getUserAddress()
				.getMunicipality(), john.getUserAddress().getRegion(), john.getUserAddress().getCountry(), john
				.getUserStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(john.getUserStatus()
				.getCreated()), DateTimeConverter.toSqlTimestamp(john.getUserStatus().getModified()));
	}

	private static void populateUserJaneViewRow(SimpleResultSet resultSet) {
		User jane = TestDataFactory.createUserJane();
		resultSet.addRow(jane.getUsername().getUsername(), jane.getUserInfo().getFirstName(), jane.getUserInfo()
				.getLastName(), DateTimeConverter.toSqlDate(jane.getUserInfo().getBirthDate()), jane.getUserInfo()
				.getGender().toString(), jane.getUserInfo().getEmail(), jane.getUserInfo().getPhone(), jane
				.getUserAddress().getAddress(), jane.getUserAddress().getPostalCode(), jane.getUserAddress()
				.getMunicipality(), jane.getUserAddress().getRegion(), jane.getUserAddress().getCountry(), jane
				.getUserStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(jane.getUserStatus()
				.getCreated()), DateTimeConverter.toSqlTimestamp(jane.getUserStatus().getModified()));
	}

	private static void populateUserJonahViewRow(SimpleResultSet resultSet) {
		User jonah = TestDataFactory.createUserJonah();
		resultSet.addRow(jonah.getUsername().getUsername(), jonah.getUserInfo().getFirstName(), jonah.getUserInfo()
				.getLastName(), DateTimeConverter.toSqlDate(jonah.getUserInfo().getBirthDate()), jonah.getUserInfo()
				.getGender().toString(), jonah.getUserInfo().getEmail(), jonah.getUserInfo().getPhone(), jonah
				.getUserAddress().getAddress(), jonah.getUserAddress().getPostalCode(), jonah.getUserAddress()
				.getMunicipality(), jonah.getUserAddress().getRegion(), jonah.getUserAddress().getCountry(), jonah
				.getUserStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(jonah.getUserStatus()
				.getCreated()), DateTimeConverter.toSqlTimestamp(jonah.getUserStatus().getModified()));
	}

	private static void populateAllUsersViewRows(SimpleResultSet resultSet) {
		populateUserJohnViewRow(resultSet);
		populateUserJaneViewRow(resultSet);
		populateUserJonahViewRow(resultSet);
	}
}
