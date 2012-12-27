package com.teoware.refapp.dao.rowmapper;

import static com.teoware.refapp.dao.metadata.UsersTableMetaData.PASSWORD_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UsersTableMetaData.SALT_COLUMN_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.model.user.UserPassword;

public class UserPasswordRowMapper implements RowMapper<UserPassword> {

	@Override
	public UserPassword mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		UserPassword authorPassword = new UserPassword();

		authorPassword.setPassword(result.getString(PASSWORD_COLUMN_NAME));
		authorPassword.setSalt(result.getString(SALT_COLUMN_NAME));

		return authorPassword;
	}
}
