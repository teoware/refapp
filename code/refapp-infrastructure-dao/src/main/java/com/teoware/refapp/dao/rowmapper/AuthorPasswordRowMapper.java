package com.teoware.refapp.dao.rowmapper;

import static com.teoware.refapp.dao.metadata.UsersTableMetaData.PASSWORD_COLUMN_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.model.author.AuthorPassword;

public class AuthorPasswordRowMapper implements RowMapper<AuthorPassword> {

	@Override
	public AuthorPassword mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		AuthorPassword authorPassword = new AuthorPassword();
		
		authorPassword.setPassword(result.getString(PASSWORD_COLUMN_NAME));
		
		return authorPassword;
	}
}
