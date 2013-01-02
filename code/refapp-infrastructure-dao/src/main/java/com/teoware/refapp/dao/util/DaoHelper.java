package com.teoware.refapp.dao.util;

import java.io.StringWriter;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public final class DaoHelper {

	public static void processParameter(PreparedStatement statement, Object parameter, int parameterIndex)
			throws SQLException {
		if (parameter == null) {
			statement.setObject(parameterIndex, parameter);
		} else if (isString(parameter) || isBigInteger(parameter)) {
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

	public static boolean isString(Object object) {
		Class<?> clazz = object.getClass();
		return String.class.isAssignableFrom(clazz) || CharSequence.class.isAssignableFrom(clazz)
				|| StringWriter.class.isAssignableFrom(clazz);
	}

	public static boolean isDate(Object object) {
		Class<?> clazz = object.getClass();
		return java.util.Date.class.isAssignableFrom(clazz) && !isSqlDateTime(object);
	}

	public static boolean isSqlDateTime(Object object) {
		Class<?> clazz = object.getClass();
		return java.sql.Date.class.isAssignableFrom(clazz) || java.sql.Time.class.isAssignableFrom(clazz)
				|| java.sql.Timestamp.class.isAssignableFrom(clazz);
	}

	public static boolean isCalendar(Object object) {
		return object instanceof Calendar;
	}

	public static boolean isBigInteger(Object object) {
		return object instanceof BigInteger;
	}

	public static Object[] generateArray(Object... objects) {
		return objects;
	}
}
