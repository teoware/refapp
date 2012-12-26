package com.teoware.refapp.util.time;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Utility class for converting between standard date/time objects and {@link DateTime}.
 * 
 * @author thomas@teoware.com
 * 
 */
public final class DateTimeConverter {

	public static TimeZone getTimeZone(DateTimeZone zone) {
		return TimeZone.getTimeZone(zone.getID());
	}

	public static DateTimeZone getDateTimeZone(TimeZone zone) {
		return DateTimeZone.forTimeZone(zone);
	}

	public static DateTime fromDate(Date date) {
		return new DateTime(date.getTime());
	}

	public static Date toDate(DateTime dateTime) {
		return new Date(dateTime.getMillis());
	}

	public static DateTime fromCalendar(Calendar calendar) {
		DateTime dateTime = new DateTime(calendar.getTimeInMillis());
		dateTime.withZone(getDateTimeZone(calendar.getTimeZone()));
		return dateTime;
	}

	public static Calendar toCalendar(DateTime dateTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(dateTime.getMillis());
		calendar.setTimeZone(getTimeZone(dateTime.getZone()));
		return calendar;
	}

	public static DateTime fromGregorianCalendar(GregorianCalendar calendar) {
		DateTime dateTime = new DateTime(calendar.getTimeInMillis());
		dateTime.withZone(getDateTimeZone(calendar.getTimeZone()));
		return dateTime;
	}

	public static GregorianCalendar toGregorianCalendar(DateTime dateTime) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTimeInMillis(dateTime.getMillis());
		calendar.setTimeZone(getTimeZone(dateTime.getZone()));
		return calendar;
	}

	public static DateTime fromSqlDate(java.sql.Date date) {
		return new DateTime(date.getTime());
	}

	public static java.sql.Date toSqlDate(DateTime dateTime) {
		return new java.sql.Date(dateTime.getMillis());
	}

	public static DateTime fromSqlTime(java.sql.Time time) {
		return new DateTime(time.getTime());
	}

	public static java.sql.Time toSqlTime(DateTime dateTime) {
		return new java.sql.Time(dateTime.getMillis());
	}

	public static DateTime fromSqlTimestamp(java.sql.Timestamp timestamp) {
		return new DateTime(timestamp.getTime());
	}

	public static java.sql.Timestamp toSqlTimestamp(DateTime dateTime) {
		return new java.sql.Timestamp(dateTime.getMillis());
	}
}
