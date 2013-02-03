package com.teoware.refapp.dao.util;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Username;

public class DaoHelper {

	public static void processParameter(PreparedStatement statement, Object parameter, int parameterIndex)
			throws SQLException {
		if (parameter == null) {
			statement.setObject(parameterIndex, parameter);
		} else if (isString(parameter)) {
			statement.setString(parameterIndex, parameter.toString());
		} else if (isInt(parameter)) {
			statement.setInt(parameterIndex, (Integer) parameter);
		} else if (isLong(parameter)) {
			statement.setLong(parameterIndex, (Long) parameter);
		} else if (isFloat(parameter)) {
			BigDecimal bd = new BigDecimal(Float.toString((Float) parameter));
			statement.setBigDecimal(parameterIndex, bd);
		} else if (isDouble(parameter)) {
			BigDecimal bd = new BigDecimal(Double.toString((Double) parameter));
			statement.setBigDecimal(parameterIndex, bd);
		} else if (isDate(parameter)) {
			java.util.Date date = (java.util.Date) parameter;
			statement.setDate(parameterIndex, new java.sql.Date(date.getTime()));
		} else if (isCalendar(parameter)) {
			Calendar calendar = (Calendar) parameter;
			statement.setTimestamp(parameterIndex, new java.sql.Timestamp(calendar.getTime().getTime()), calendar);
		} else if (isEnum(parameter)) {
			statement.setString(parameterIndex, parameter.toString());
		} else if (isId(parameter)) {
			Id id = (Id) parameter;
			statement.setLong(parameterIndex, id.getId());
		} else if (isUsername(parameter)) {
			Username username = (Username) parameter;
			statement.setString(parameterIndex, username.getUsername());
		} else {
			statement.setObject(parameterIndex, parameter);
		}
	}

	public static boolean isString(Object object) {
		Class<?> clazz = object.getClass();
		return String.class.isAssignableFrom(clazz) || CharSequence.class.isAssignableFrom(clazz)
				|| StringWriter.class.isAssignableFrom(clazz) || StringBuffer.class.isAssignableFrom(clazz);
	}

	public static boolean isInt(Object object) {
		return object instanceof Integer;
	}

	public static boolean isLong(Object object) {
		return object instanceof Long;
	}

	public static boolean isFloat(Object object) {
		return object instanceof Float;
	}

	public static boolean isDouble(Object object) {
		return object instanceof Double;
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

	public static boolean isEnum(Object object) {
		return object.getClass().isEnum();
	}

	public static boolean isId(Object object) {
		return object instanceof Id;
	}

	public static boolean isUsername(Object object) {
		return object instanceof Username;
	}

	public static Object[] generateArray(Object... objects) {
		return objects;
	}
}
