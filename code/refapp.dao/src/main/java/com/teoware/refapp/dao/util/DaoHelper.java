package com.teoware.refapp.dao.util;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class DaoHelper {

	public static void processParameter(PreparedStatement statement, Object parameter, int parameterIndex) throws SQLException {
		if (parameter == null) {
			statement.setObject(parameterIndex, parameter);
		} else if (isString(parameter.getClass())) {
			statement.setString(parameterIndex, parameter.toString());
		} else if (isDate(parameter.getClass())) {
			java.util.Date date = (java.util.Date) parameter;
			statement.setDate(parameterIndex, new java.sql.Date(date.getTime()));
		} else if (isCalendar(parameter.getClass())) {
			Calendar calendar = (Calendar) parameter;
			statement.setTimestamp(parameterIndex, new java.sql.Timestamp(calendar.getTime().getTime()), calendar);
		} else {
			statement.setObject(parameterIndex, parameter);
		}
	}

	private static boolean isString(Class<?> value) {
		return (CharSequence.class.isAssignableFrom(value) || StringWriter.class.isAssignableFrom(value));
	}

	private static boolean isDate(Class<?> value) {
		return (java.util.Date.class.isAssignableFrom(value) &&
				!(java.sql.Date.class.isAssignableFrom(value) ||
						java.sql.Time.class.isAssignableFrom(value) ||
						java.sql.Timestamp.class.isAssignableFrom(value)));
	}

	private static boolean isCalendar(Class<?> value) {
		return Calendar.class.equals(value);
	}
}
