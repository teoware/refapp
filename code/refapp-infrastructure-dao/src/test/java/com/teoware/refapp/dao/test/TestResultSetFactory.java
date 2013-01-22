package com.teoware.refapp.dao.test;

import static com.teoware.refapp.dao.metadata.UserTables.ADDRESS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.BIRTHDATE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.COUNTRY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.CREATED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.EMAIL_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.FIRSTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.GENDER_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.LASTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.MODIFIED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.MUNICIPALITY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.PHONE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.POSTALCODE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.REGION_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.STATUS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.USERNAME_COLUMN_NAME;

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
		resultSet.addRow(john.getUsername().getUsername(), john.getUserDetails().getFirstName(), john.getUserDetails()
				.getLastName(), DateTimeConverter.toSqlDate(john.getUserDetails().getBirthDate()), john.getUserDetails()
				.getGender().toString(), john.getUserDetails().getEmail(), john.getUserDetails().getPhone(), john
				.getUserAddress().getAddress(), john.getUserAddress().getPostalCode(), john.getUserAddress()
				.getMunicipality(), john.getUserAddress().getRegion(), john.getUserAddress().getCountry(), john
				.getUserStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(john.getUserStatus()
				.getCreated()), DateTimeConverter.toSqlTimestamp(john.getUserStatus().getModified()));
	}

	private static void populateUserJaneViewRow(SimpleResultSet resultSet) {
		User jane = TestDataFactory.createUserJane();
		resultSet.addRow(jane.getUsername().getUsername(), jane.getUserDetails().getFirstName(), jane.getUserDetails()
				.getLastName(), DateTimeConverter.toSqlDate(jane.getUserDetails().getBirthDate()), jane.getUserDetails()
				.getGender().toString(), jane.getUserDetails().getEmail(), jane.getUserDetails().getPhone(), jane
				.getUserAddress().getAddress(), jane.getUserAddress().getPostalCode(), jane.getUserAddress()
				.getMunicipality(), jane.getUserAddress().getRegion(), jane.getUserAddress().getCountry(), jane
				.getUserStatus().getStatus().toString(), DateTimeConverter.toSqlTimestamp(jane.getUserStatus()
				.getCreated()), DateTimeConverter.toSqlTimestamp(jane.getUserStatus().getModified()));
	}

	private static void populateUserJonahViewRow(SimpleResultSet resultSet) {
		User jonah = TestDataFactory.createUserJonah();
		resultSet.addRow(jonah.getUsername().getUsername(), jonah.getUserDetails().getFirstName(), jonah.getUserDetails()
				.getLastName(), DateTimeConverter.toSqlDate(jonah.getUserDetails().getBirthDate()), jonah.getUserDetails()
				.getGender().toString(), jonah.getUserDetails().getEmail(), jonah.getUserDetails().getPhone(), jonah
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
