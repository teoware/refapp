package com.teoware.refapp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public interface RowMapper<T> {

    T mapRow(ResultSet result, int rowCount) throws SQLException, ParseException;
}
