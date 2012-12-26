package com.teoware.refapp.dao.rowmapper;

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
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.dao.rowmapper.util.MapperHelper;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.util.BeanFactory;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		User user = BeanFactory.createUserBean();
		
		user.getUserId().setUserName(result.getString(USERNAME_COLUMN_NAME));
		user.getUserId().setStatus(MapperHelper.mapUserStatus(result.getString(STATUS_COLUMN_NAME)));
		user.getUserId().setCreated(MapperHelper.mapCalendar(result.getTimestamp(CREATED_COLUMN_NAME)));
		user.getUserId().setModified(MapperHelper.mapCalendar(result.getTimestamp(MODIFIED_COLUMN_NAME)));
		
		user.getUserInfo().setFirstName(result.getString(FIRSTNAME_COLUMN_NAME));
		user.getUserInfo().setLastName(result.getString(LASTNAME_COLUMN_NAME));
		user.getUserInfo().setBirthDate(MapperHelper.mapDate(result.getDate(BIRTHDATE_COLUMN_NAME)));
		user.getUserInfo().setGender(MapperHelper.mapGender(result.getString(GENDER_COLUMN_NAME)));
		user.getUserInfo().setEmail(result.getString(EMAIL_COLUMN_NAME));
		user.getUserInfo().setPhone(result.getString(PHONE_COLUMN_NAME));
		
		user.getUserAddress().setAddress(result.getString(ADDRESS_COLUMN_NAME));
		user.getUserAddress().setPostalCode(result.getString(POSTALCODE_COLUMN_NAME));
		user.getUserAddress().setMunicipality(result.getString(MUNICIPALITY_COLUMN_NAME));
		user.getUserAddress().setRegion(result.getString(REGION_COLUMN_NAME));
		user.getUserAddress().setCountry(result.getString(COUNTRY_COLUMN_NAME));
		
		return user;
	}
}