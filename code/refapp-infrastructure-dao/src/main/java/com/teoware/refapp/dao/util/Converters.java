package com.teoware.refapp.dao.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Converters {

	private Converters() {
	}

	public static interface Converter<T> {
		public T convert(Object param);

		public void setParam(PreparedStatement statement, Object param,
				int index) throws SQLException;
	}

	public static class StringConverter implements Converter<String> {
		@Override
		public String convert(Object param) {
			return param.toString();
		}

		@Override
		public void setParam(PreparedStatement statement, Object param,
				int index) throws SQLException {
			statement.setString(index, convert(param));
		}
	}
}
