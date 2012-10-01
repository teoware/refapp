package com.teoware.refapp.dao.util;

import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.ADDRESS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.BIRTHDATE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.COUNTRY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.CREATED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.EMAIL_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.FIRSTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.GENDER_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.LASTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.MODIFIED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.MUNICIPALITY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.PHONE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.POSTALCODE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.REGION_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.STATUS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.USERNAME_COLUMN_NAME;

import java.sql.ResultSet;
import java.text.ParseException;

import org.apache.derby.client.am.Types;
import org.h2.tools.SimpleResultSet;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.test.util.TestDataFactory;
import com.teoware.refapp.util.DateUtils;

public class TestResultSetFactory {

	public static ResultSet createSelectAuthorResultSet() throws ParseException {
		SimpleResultSet resultSet = new SimpleResultSet();
		populateAuthorViewColumns(resultSet);
		populateAuthorViewRows(resultSet);
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

	private static void populateAuthorViewRows(SimpleResultSet resultSet) throws ParseException {
		Author john = TestDataFactory.createAuthorJohn();
		resultSet.addRow(
				john.getAuthorId().getUserName(),
				john.getAuthorInfo().getFirstName(),
				john.getAuthorInfo().getLastName(),
				DateUtils.dateToSqlDate(john.getAuthorInfo().getBirthDate()),
				john.getAuthorInfo().getGender().toString(),
				john.getAuthorInfo().getEmail(),
				john.getAuthorInfo().getPhone(),
				john.getAuthorAddress().getAddress(),
				john.getAuthorAddress().getPostalCode(),
				john.getAuthorAddress().getMunicipality(),
				john.getAuthorAddress().getRegion(),
				john.getAuthorAddress().getCountry(),
				john.getAuthorId().getStatus().toString(),
				DateUtils.calendarToSqlTimestamp(john.getAuthorId().getCreated()),
				DateUtils.calendarToSqlTimestamp(john.getAuthorId().getModified()));
		Author jane = TestDataFactory.createAuthorJane();
		resultSet.addRow(
				jane.getAuthorId().getUserName(),
				jane.getAuthorInfo().getFirstName(),
				jane.getAuthorInfo().getLastName(),
				DateUtils.dateToSqlDate(jane.getAuthorInfo().getBirthDate()),
				jane.getAuthorInfo().getGender().toString(),
				jane.getAuthorInfo().getEmail(),
				jane.getAuthorInfo().getPhone(),
				jane.getAuthorAddress().getAddress(),
				jane.getAuthorAddress().getPostalCode(),
				jane.getAuthorAddress().getMunicipality(),
				jane.getAuthorAddress().getRegion(),
				jane.getAuthorAddress().getCountry(),
				jane.getAuthorId().getStatus().toString(),
				DateUtils.calendarToSqlTimestamp(jane.getAuthorId().getCreated()),
				DateUtils.calendarToSqlTimestamp(jane.getAuthorId().getModified()));
		Author jonah = TestDataFactory.createAuthorJonah();
		resultSet.addRow(
				jonah.getAuthorId().getUserName(),
				jonah.getAuthorInfo().getFirstName(),
				jonah.getAuthorInfo().getLastName(),
				DateUtils.dateToSqlDate(jonah.getAuthorInfo().getBirthDate()),
				jonah.getAuthorInfo().getGender().toString(),
				jonah.getAuthorInfo().getEmail(),
				jonah.getAuthorInfo().getPhone(),
				jonah.getAuthorAddress().getAddress(),
				jonah.getAuthorAddress().getPostalCode(),
				jonah.getAuthorAddress().getMunicipality(),
				jonah.getAuthorAddress().getRegion(),
				jonah.getAuthorAddress().getCountry(),
				jonah.getAuthorId().getStatus().toString(),
				DateUtils.calendarToSqlTimestamp(jonah.getAuthorId().getCreated()),
				DateUtils.calendarToSqlTimestamp(jonah.getAuthorId().getModified()));
	}
}
