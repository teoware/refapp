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

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.util.time.DateTimeUtils;

public final class TestResultSetFactory {

	public static ResultSet createSelectAllAuthorsResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateAuthorViewColumns(resultSet);
		populateAllAuthorsViewRows(resultSet);
		return resultSet;
	}

	public static ResultSet createSelectAuthorJohnResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateAuthorViewColumns(resultSet);
		populateAuthorJohnViewRow(resultSet);
		return resultSet;
	}

	public static ResultSet createSelectAuthorJaneResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateAuthorViewColumns(resultSet);
		populateAllAuthorsViewRows(resultSet);
		return resultSet;
	}

	public static ResultSet createSelectAuthorJonahResultSet() {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateAuthorViewColumns(resultSet);
		populateAllAuthorsViewRows(resultSet);
		return resultSet;
	}

	private static void populateAuthorViewColumns(SimpleResultSet resultSet) {
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

	private static void populateAuthorJohnViewRow(SimpleResultSet resultSet) {
		Author john = TestDataFactory.createAuthorJohn();
		resultSet.addRow(john.getAuthorId().getUserName(), john.getAuthorInfo().getFirstName(), john.getAuthorInfo()
				.getLastName(), DateTimeUtils.dateToSqlDate(john.getAuthorInfo().getBirthDate()), john.getAuthorInfo()
				.getGender().toString(), john.getAuthorInfo().getEmail(), john.getAuthorInfo().getPhone(), john
				.getAuthorAddress().getAddress(), john.getAuthorAddress().getPostalCode(), john.getAuthorAddress()
				.getMunicipality(), john.getAuthorAddress().getRegion(), john.getAuthorAddress().getCountry(), john
				.getAuthorId().getStatus().toString(), DateTimeUtils
				.calendarToSqlTimestamp(john.getAuthorId().getCreated()), DateTimeUtils.calendarToSqlTimestamp(john
				.getAuthorId().getModified()));
	}

	private static void populateAuthorJaneViewRow(SimpleResultSet resultSet) {
		Author jane = TestDataFactory.createAuthorJane();
		resultSet.addRow(jane.getAuthorId().getUserName(), jane.getAuthorInfo().getFirstName(), jane.getAuthorInfo()
				.getLastName(), DateTimeUtils.dateToSqlDate(jane.getAuthorInfo().getBirthDate()), jane.getAuthorInfo()
				.getGender().toString(), jane.getAuthorInfo().getEmail(), jane.getAuthorInfo().getPhone(), jane
				.getAuthorAddress().getAddress(), jane.getAuthorAddress().getPostalCode(), jane.getAuthorAddress()
				.getMunicipality(), jane.getAuthorAddress().getRegion(), jane.getAuthorAddress().getCountry(), jane
				.getAuthorId().getStatus().toString(), DateTimeUtils
				.calendarToSqlTimestamp(jane.getAuthorId().getCreated()), DateTimeUtils.calendarToSqlTimestamp(jane
				.getAuthorId().getModified()));
	}

	private static void populateAuthorJonahViewRow(SimpleResultSet resultSet) {
		Author jonah = TestDataFactory.createAuthorJonah();
		resultSet.addRow(jonah.getAuthorId().getUserName(), jonah.getAuthorInfo().getFirstName(), jonah.getAuthorInfo()
				.getLastName(), DateTimeUtils.dateToSqlDate(jonah.getAuthorInfo().getBirthDate()), jonah.getAuthorInfo()
				.getGender().toString(), jonah.getAuthorInfo().getEmail(), jonah.getAuthorInfo().getPhone(), jonah
				.getAuthorAddress().getAddress(), jonah.getAuthorAddress().getPostalCode(), jonah.getAuthorAddress()
				.getMunicipality(), jonah.getAuthorAddress().getRegion(), jonah.getAuthorAddress().getCountry(), jonah
				.getAuthorId().getStatus().toString(), DateTimeUtils.calendarToSqlTimestamp(jonah.getAuthorId()
				.getCreated()), DateTimeUtils.calendarToSqlTimestamp(jonah.getAuthorId().getModified()));
	}

	private static void populateAllAuthorsViewRows(SimpleResultSet resultSet) {
		populateAuthorJohnViewRow(resultSet);
		populateAuthorJaneViewRow(resultSet);
		populateAuthorJonahViewRow(resultSet);
	}
}
