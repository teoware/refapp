package com.teoware.refapp.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public interface ResultSetExtractor<T> {

	T extractData(ResultSet result) throws SQLException, ParseException;
}
