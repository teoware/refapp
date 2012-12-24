package com.teoware.refapp.dao.test;

import static com.teoware.refapp.dao.metadata.UsersTableMetaData.ADDRESS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.BIRTHDATE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.COUNTRY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.CREATED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.EMAIL_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.FIRSTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.GENDER_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.LASTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.MODIFIED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.MUNICIPALITY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.PHONE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.POSTALCODE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.REGION_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.STATUS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.USERNAME_COLUMN_NAME;

import java.sql.ResultSet;

import org.apache.derby.client.am.Types;
import org.h2.tools.SimpleResultSet;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.util.time.DateTimeUtils;

public final class TestResultSetFactory {

	public static ResultSet createSelectAllUsersResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUserViewColumns(resultSet);
		populateAllUsersViewRows(resultSet);
		return resultSet;
	}

	public static ResultSet createSelectUserJohnResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUserViewColumns(resultSet);
		populateUserJohnViewRow(resultSet);
		return resultSet;
	}

	public static ResultSet createSelectUserJaneResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateUserViewColumns(resultSet);
		populateAllUsersViewRows(resultSet);
		return resultSet;
	}

	public static ResultSet createSelectUserJonahResultSet() {
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
		resultSet.addRow(john.getUserId().getUserName(), john.getUserInfo().getFirstName(), john.getUserInfo()
				.getLastName(), DateTimeUtils.dateToSqlDate(john.getUserInfo().getBirthDate()), john.getUserInfo()
				.getGender().toString(), john.getUserInfo().getEmail(), john.getUserInfo().getPhone(), john
				.getUserAddress().getAddress(), john.getUserAddress().getPostalCode(), john.getUserAddress()
				.getMunicipality(), john.getUserAddress().getRegion(), john.getUserAddress().getCountry(), john
				.getUserId().getStatus().toString(), DateTimeUtils
				.calendarToSqlTimestamp(john.getUserId().getCreated()), DateTimeUtils.calendarToSqlTimestamp(john
				.getUserId().getModified()));
	}

	private static void populateUserJaneViewRow(SimpleResultSet resultSet) {
		User jane = TestDataFactory.createUserJane();
		resultSet.addRow(jane.getUserId().getUserName(), jane.getUserInfo().getFirstName(), jane.getUserInfo()
				.getLastName(), DateTimeUtils.dateToSqlDate(jane.getUserInfo().getBirthDate()), jane.getUserInfo()
				.getGender().toString(), jane.getUserInfo().getEmail(), jane.getUserInfo().getPhone(), jane
				.getUserAddress().getAddress(), jane.getUserAddress().getPostalCode(), jane.getUserAddress()
				.getMunicipality(), jane.getUserAddress().getRegion(), jane.getUserAddress().getCountry(), jane
				.getUserId().getStatus().toString(), DateTimeUtils
				.calendarToSqlTimestamp(jane.getUserId().getCreated()), DateTimeUtils.calendarToSqlTimestamp(jane
				.getUserId().getModified()));
	}

	private static void populateUserJonahViewRow(SimpleResultSet resultSet) {
		User jonah = TestDataFactory.createUserJonah();
		resultSet.addRow(jonah.getUserId().getUserName(), jonah.getUserInfo().getFirstName(), jonah.getUserInfo()
				.getLastName(), DateTimeUtils.dateToSqlDate(jonah.getUserInfo().getBirthDate()), jonah.getUserInfo()
				.getGender().toString(), jonah.getUserInfo().getEmail(), jonah.getUserInfo().getPhone(), jonah
				.getUserAddress().getAddress(), jonah.getUserAddress().getPostalCode(), jonah.getUserAddress()
				.getMunicipality(), jonah.getUserAddress().getRegion(), jonah.getUserAddress().getCountry(), jonah
				.getUserId().getStatus().toString(), DateTimeUtils.calendarToSqlTimestamp(jonah.getUserId()
				.getCreated()), DateTimeUtils.calendarToSqlTimestamp(jonah.getUserId().getModified()));
	}

	private static void populateAllUsersViewRows(SimpleResultSet resultSet) {
		populateUserJohnViewRow(resultSet);
		populateUserJaneViewRow(resultSet);
		populateUserJonahViewRow(resultSet);
	}
}
