package com.teoware.refapp.dao.rowmapper;

import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.ADDRESS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.BIRTHDATE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.COUNTRY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.CREATED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.EMAIL_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.FIRSTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.GENDER_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.LASTNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.MODIFIED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.MUNICIPALITY_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.PHONE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.POSTALCODE_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.REGION_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.STATUS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.AuthorsTableMetaData.USERNAME_COLUMN_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.dao.rowmapper.util.MapperHelper;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.util.BeanFactory;

public class AuthorRowMapper implements RowMapper<Author> {

	@Override
	public Author mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		Author author = BeanFactory.createAuthorBean();
		
		author.getAuthorId().setUserName(result.getString(USERNAME_COLUMN_NAME));
		author.getAuthorId().setStatus(MapperHelper.mapAuthorStatus(result.getString(STATUS_COLUMN_NAME)));
		author.getAuthorId().setCreated(MapperHelper.mapCalendar(result.getTimestamp(CREATED_COLUMN_NAME)));
		author.getAuthorId().setModified(MapperHelper.mapCalendar(result.getTimestamp(MODIFIED_COLUMN_NAME)));
		
		author.getAuthorInfo().setFirstName(result.getString(FIRSTNAME_COLUMN_NAME));
		author.getAuthorInfo().setLastName(result.getString(LASTNAME_COLUMN_NAME));
		author.getAuthorInfo().setBirthDate(MapperHelper.mapDate(result.getDate(BIRTHDATE_COLUMN_NAME)));
		author.getAuthorInfo().setGender(MapperHelper.mapGender(result.getString(GENDER_COLUMN_NAME)));
		author.getAuthorInfo().setEmail(result.getString(EMAIL_COLUMN_NAME));
		author.getAuthorInfo().setPhone(result.getString(PHONE_COLUMN_NAME));
		
		author.getAuthorAddress().setAddress(result.getString(ADDRESS_COLUMN_NAME));
		author.getAuthorAddress().setPostalCode(result.getString(POSTALCODE_COLUMN_NAME));
		author.getAuthorAddress().setMunicipality(result.getString(MUNICIPALITY_COLUMN_NAME));
		author.getAuthorAddress().setRegion(result.getString(REGION_COLUMN_NAME));
		author.getAuthorAddress().setCountry(result.getString(COUNTRY_COLUMN_NAME));
		
		return author;
	}
}
