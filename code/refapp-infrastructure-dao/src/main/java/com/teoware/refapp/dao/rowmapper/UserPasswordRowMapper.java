package com.teoware.refapp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.dao.metadata.UserTables;
import com.teoware.refapp.model.user.UserPassword;

public class UserPasswordRowMapper implements RowMapper<UserPassword> {

    @Override
    public UserPassword mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
        UserPassword authorPassword = new UserPassword();

        authorPassword.setPassword(result.getString(UserTables.PASSWORD_COLUMN_NAME));
        authorPassword.setSalt(result.getString(UserTables.SALT_COLUMN_NAME));

        return authorPassword;
    }
}
