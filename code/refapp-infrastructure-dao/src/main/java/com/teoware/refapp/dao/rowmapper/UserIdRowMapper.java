package com.teoware.refapp.dao.rowmapper;

import static com.teoware.refapp.dao.metadata.UsersTableMetaData.ID_COLUMN_NAME;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class UserIdRowMapper implements RowMapper<BigInteger> {

	@Override
	public BigInteger mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		BigDecimal userId = result.getBigDecimal(ID_COLUMN_NAME);
		if (userId != null) {
			return userId.toBigInteger();
		} else {
			return null;
		}
	}
}
