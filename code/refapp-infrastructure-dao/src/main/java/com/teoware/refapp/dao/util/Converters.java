package com.teoware.refapp.dao.util;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Username;
import com.teoware.refapp.model.common.Uuid;
import com.teoware.refapp.util.time.DateTimeConverter;
import com.teoware.refapp.util.time.DateTimeUtils;

public final class Converters {

	private static Map<Class<?>, Converter<?>> converters = new HashMap<Class<?>, Converter<?>>();

	static {
		converters.put(Object.class, new ObjectConverter());
		converters.put(String.class, new StringConverter());
		converters.put(Integer.class, new IntConverter());
		converters.put(Long.class, new LongConverter());
		converters.put(Float.class, new BigDecimalConverter());
		converters.put(Double.class, new BigDecimalConverter());
		converters.put(BigDecimal.class, new BigDecimalConverter());
		converters.put(java.sql.Date.class, new DateConverter());
		converters.put(java.util.Date.class, new DateConverter());
		converters.put(java.sql.Timestamp.class, new TimestampConverter());
		converters.put(DateTime.class, new TimestampConverter());
		converters.put(GregorianCalendar.class, new TimestampConverter());
		converters.put(Id.class, new IdConverter());
		converters.put(Username.class, new UsernameConverter());
		converters.put(Uuid.class, new UuidConverter());
	}

	private Converters() {
	}

	public static Map<Class<?>, Converter<?>> getConverters() {
		return converters;
	}

	public static interface Converter<T> {
		public T convert(Object param);

		public void setParam(PreparedStatement statement, Object param, int index) throws SQLException;
	}

	public static class ObjectConverter implements Converter<Object> {
		@Override
		public Object convert(Object param) {
			return param;
		}

		@Override
		public void setParam(PreparedStatement statement, Object param, int index) throws SQLException {
			statement.setObject(index, convert(param));
		}
	}

	public static class StringConverter implements Converter<String> {
		@Override
		public String convert(Object param) {
			return param.toString();
		}

		@Override
		public void setParam(PreparedStatement statement, Object param, int index) throws SQLException {
			statement.setString(index, convert(param));
		}
	}

	public static class IntConverter implements Converter<Integer> {
		@Override
		public Integer convert(Object param) {
			return (Integer) param;
		}

		@Override
		public void setParam(PreparedStatement statement, Object param, int index) throws SQLException {
			statement.setInt(index, convert(param));
		}
	}

	public static class LongConverter implements Converter<Long> {
		@Override
		public Long convert(Object param) {
			return (Long) param;
		}

		@Override
		public void setParam(PreparedStatement statement, Object param, int index) throws SQLException {
			statement.setLong(index, convert(param));
		}
	}

	public static class BigDecimalConverter implements Converter<BigDecimal> {
		@Override
		public BigDecimal convert(Object param) {
			if (param instanceof Float) {
				return new BigDecimal(Float.toString((Float) param));
			} else if (param instanceof Double) {
				return new BigDecimal(Double.toString((Double) param));
			} else {
				return (BigDecimal) param;
			}
		}

		@Override
		public void setParam(PreparedStatement statement, Object param, int index) throws SQLException {
			statement.setBigDecimal(index, convert(param));
		}
	}

	public static class DateConverter implements Converter<java.sql.Date> {
		@Override
		public java.sql.Date convert(Object param) {
			return DateTimeUtils.dateToSqlDate((java.util.Date) param);
		}

		@Override
		public void setParam(PreparedStatement statement, Object param, int index) throws SQLException {
			statement.setDate(index, convert(param));
		}
	}

	public static class TimestampConverter implements Converter<java.sql.Timestamp> {
		@Override
		public java.sql.Timestamp convert(Object param) {
			if (param instanceof DateTime) {
				return DateTimeConverter.toSqlTimestamp((DateTime) param);
			} else if (param instanceof GregorianCalendar) {
				return DateTimeUtils.calendarToSqlTimestamp((Calendar) param);
			} else {
				return (java.sql.Timestamp) param;
			}
		}

		@Override
		public void setParam(PreparedStatement statement, Object param, int index) throws SQLException {
			statement.setTimestamp(index, convert(param));
		}
	}

	public static class IdConverter implements Converter<Long> {
		@Override
		public Long convert(Object param) {
			return ((Id) param).getId();
		}

		@Override
		public void setParam(PreparedStatement statement, Object param, int index) throws SQLException {
			statement.setLong(index, convert(param));
		}
	}

	public static class UsernameConverter implements Converter<String> {
		@Override
		public String convert(Object param) {
			return ((Username) param).getUsername();
		}

		@Override
		public void setParam(PreparedStatement statement, Object param, int index) throws SQLException {
			statement.setString(index, convert(param));
		}
	}

	public static class UuidConverter implements Converter<String> {
		@Override
		public String convert(Object param) {
			return ((Uuid) param).getUuid();
		}

		@Override
		public void setParam(PreparedStatement statement, Object param, int index) throws SQLException {
			statement.setString(index, convert(param));
		}
	}
}
