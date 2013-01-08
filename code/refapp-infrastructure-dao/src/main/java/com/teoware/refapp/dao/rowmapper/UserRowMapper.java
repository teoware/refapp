package com.teoware.refapp.dao.rowmapper;

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
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.dao.util.MapperHelper;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.util.BeanFactory;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		User user = BeanFactory.createUserBean();

		user.getUsername().setUsername(result.getString(USERNAME_COLUMN_NAME));

		user.getUserStatus().setStatus(MapperHelper.mapStatus(result.getString(STATUS_COLUMN_NAME)));
		user.getUserStatus().setCreated(MapperHelper.mapTimestamp(result.getTimestamp(CREATED_COLUMN_NAME)));
		user.getUserStatus().setModified(MapperHelper.mapTimestamp(result.getTimestamp(MODIFIED_COLUMN_NAME)));

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
