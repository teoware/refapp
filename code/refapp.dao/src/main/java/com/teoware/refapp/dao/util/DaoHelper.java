package com.teoware.refapp.dao.util;

import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class DaoHelper {

	public static void processParameter(PreparedStatement statement, Object parameter, int parameterIndex) throws SQLException {
		if (parameter == null) {
			statement.setObject(parameterIndex, parameter);
		} else if (isString(parameter)) {
			statement.setString(parameterIndex, parameter.toString());
		} else if (isDate(parameter)) {
			java.util.Date date = (java.util.Date) parameter;
			statement.setDate(parameterIndex, new java.sql.Date(date.getTime()));
		} else if (isCalendar(parameter)) {
			Calendar calendar = (Calendar) parameter;
			statement.setTimestamp(parameterIndex, new java.sql.Timestamp(calendar.getTime().getTime()), calendar);
		} else {
			statement.setObject(parameterIndex, parameter);
		}
	}

	private static boolean isString(Object object) {
		Class<?> clazz = object.getClass();
		return (CharSequence.class.isAssignableFrom(clazz) || StringWriter.class.isAssignableFrom(clazz));
	}

	private static boolean isDate(Object object) {
		Class<?> clazz = object.getClass();
		return (java.util.Date.class.isAssignableFrom(clazz) &&
				!(java.sql.Date.class.isAssignableFrom(clazz) ||
						java.sql.Time.class.isAssignableFrom(clazz) ||
						java.sql.Timestamp.class.isAssignableFrom(clazz)));
	}

	private static boolean isCalendar(Object object) {
		return object instanceof Calendar;
	}

	public static Object[] generateArray(Object... objects) {
		return objects;
	}
}
