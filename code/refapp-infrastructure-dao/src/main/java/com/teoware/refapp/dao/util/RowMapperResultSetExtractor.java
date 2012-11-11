package com.teoware.refapp.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.teoware.refapp.dao.rowmapper.RowMapper;

public class RowMapperResultSetExtractor<T> implements ResultSetExtractor<List<T>> {

	private final RowMapper<T> rowMapper;
	private final int rowsExpected;

	public RowMapperResultSetExtractor(RowMapper<T> rowMapper) {
		this(rowMapper, 0);
	}

	public RowMapperResultSetExtractor(RowMapper<T> rowMapper, int rowsExpected) {
		this.rowMapper = rowMapper;
		this.rowsExpected = rowsExpected;
	}

	@Override
	public List<T> extractData(ResultSet result) throws SQLException, ParseException {
		List<T> resultList = (this.rowsExpected > 0 ? new ArrayList<T>(this.rowsExpected) : new ArrayList<T>());
		int rowNum = 0;
		while (result.next()) {
			resultList.add(this.rowMapper.mapRow(result, rowNum++));
		}
		return resultList;
	}
}
