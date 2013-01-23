package com.teoware.refapp.dao.rowmapper;

import static com.teoware.refapp.dao.metadata.UserTables.ID_COLUMN_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.dao.dto.Id;

public class UserIdRowMapper implements RowMapper<Id> {

	@Override
	public Id mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		return new Id(result.getLong(ID_COLUMN_NAME));
	}
}
