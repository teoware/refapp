package com.teoware.refapp.dao.rowmapper;

import static com.teoware.refapp.dao.metadata.UserTables.USERNAME_COLUMN_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.dao.metadata.UserTables;
import com.teoware.refapp.dao.util.MapperHelper;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.util.BeanFactory;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		User user = BeanFactory.createUserBean();

		user.getUsername().setUsername(result.getString(USERNAME_COLUMN_NAME));

		user.getUserStatus().setStatus(MapperHelper.mapStatus(result.getString(UserTables.STATUS_COLUMN_NAME)));
		user.getUserStatus().setCreated(MapperHelper.mapTimestamp(result.getTimestamp(UserTables.CREATED_COLUMN_NAME)));
		user.getUserStatus().setModified(
				MapperHelper.mapTimestamp(result.getTimestamp(UserTables.MODIFIED_COLUMN_NAME)));

		user.getUserDetails().setFirstName(result.getString(UserTables.FIRSTNAME_COLUMN_NAME));
		user.getUserDetails().setLastName(result.getString(UserTables.LASTNAME_COLUMN_NAME));
		user.getUserDetails().setBirthDate(MapperHelper.mapDate(result.getDate(UserTables.BIRTHDATE_COLUMN_NAME)));
		user.getUserDetails().setGender(MapperHelper.mapGender(result.getString(UserTables.GENDER_COLUMN_NAME)));
		user.getUserDetails().setEmail(result.getString(UserTables.EMAIL_COLUMN_NAME));
		user.getUserDetails().setPhone(result.getString(UserTables.PHONE_COLUMN_NAME));

		user.getUserAddress().setAddress(result.getString(UserTables.ADDRESS_COLUMN_NAME));
		user.getUserAddress().setPostalCode(result.getString(UserTables.POSTALCODE_COLUMN_NAME));
		user.getUserAddress().setMunicipality(result.getString(UserTables.MUNICIPALITY_COLUMN_NAME));
		user.getUserAddress().setRegion(result.getString(UserTables.REGION_COLUMN_NAME));
		user.getUserAddress().setCountry(result.getString(UserTables.COUNTRY_COLUMN_NAME));

		return user;
	}
}
