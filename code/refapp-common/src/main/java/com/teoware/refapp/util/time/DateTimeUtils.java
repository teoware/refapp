package com.teoware.refapp.util.time;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;

/**
 * Utility class for converting between basic Java date/time objects, and for
 * converting to/from a date/time formatted strings.
 * 
 * @author thomas@teoware.com
 * 
 */
public final class DateTimeUtils {

	public static final int SECOND_IN_MILLIS = 1000;
	public static final int MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60;
	public static final int HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
	public static final int DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;

	public static Calendar createCalendar(int field, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getDefault());
		calendar.roll(field, offset);
		return calendar;
	}

	public static Date createDate(int field, int offset) {
		return createCalendar(field, offset).getTime();
	}

	public static Date stringToDate(String dateString) {
		return stringToDate(dateString, DateTimeParser.DATE_PATTERN);
	}

	public static Date stringToDate(String dateString, String pattern) {
		DateTime dateTime = DateTimeParser.fromString(dateString, pattern);
		return DateTimeConverter.toDate(dateTime);
	}

	public static String dateToString(Date date) {
		return dateToString(date, DateTimeParser.DATE_PATTERN);
	}

	public static String dateToString(Date date, String pattern) {
		DateTime dateTime = DateTimeConverter.fromDate(date);
		return DateTimeParser.toString(dateTime, pattern);
	}

	public static Calendar stringToCalendar(String dateString) {
		return stringToCalendar(dateString, DateTimeParser.DATE_PATTERN);
	}

	public static Calendar stringToCalendar(String dateString, String pattern) {
		DateTime dateTime = DateTimeParser.fromString(dateString, pattern);
		return DateTimeConverter.toCalendar(dateTime);
	}

	public static String calendarToString(Calendar calendar) {
		return calendarToString(calendar, DateTimeParser.DATE_PATTERN);
	}

	public static String calendarToString(Calendar calendar, String pattern) {
		DateTime dateTime = DateTimeConverter.fromCalendar(calendar);
		return DateTimeParser.toString(dateTime, pattern);
	}

	public static Date timestampToDate(String timestampString) {
		return stringToDate(timestampString, DateTimeParser.DATETIME_PATTERN);
	}

	public static String dateToTimestamp(Date date) {
		return dateToString(date, DateTimeParser.DATETIME_PATTERN);
	}

	public static Calendar timestampToCalendar(String dateString) {
		return stringToCalendar(dateString, DateTimeParser.DATETIME_PATTERN);
	}

	public static String calendarToTimestamp(Calendar calendar) {
		return calendarToString(calendar, DateTimeParser.DATETIME_PATTERN);
	}

	public static java.sql.Date dateToSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static Date sqlDateToDate(java.sql.Date date) {
		return new Date(date.getTime());
	}

	public static java.sql.Time dateToSqlTime(Date date) {
		return new java.sql.Time(date.getTime());
	}

	public static Date sqlTimeToDate(java.sql.Time time) {
		return new Date(time.getTime());
	}

	public static java.sql.Timestamp dateToSqlTimestamp(Date date) {
		return new java.sql.Timestamp(date.getTime());
	}

	public static Date sqlTimestampToDate(java.sql.Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	public static java.sql.Date calendarToSqlDate(Calendar calendar) {
		return new java.sql.Date(calendar.getTimeInMillis());
	}

	public static Calendar sqlDateToCalendar(java.sql.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		return calendar;
	}

	public static java.sql.Time calendarToSqlTime(Calendar calendar) {
		return new java.sql.Time(calendar.getTimeInMillis());
	}

	public static Calendar sqlTimeToCalendar(java.sql.Time time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time.getTime());
		return calendar;
	}

	public static java.sql.Timestamp calendarToSqlTimestamp(Calendar calendar) {
		return new java.sql.Timestamp(calendar.getTimeInMillis());
	}

	public static Calendar sqlTimestampToCalendar(java.sql.Timestamp timestamp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(timestamp.getTime());
		return calendar;
	}

	public static java.sql.Date sqlTimestampToSqlDate(java.sql.Timestamp timestamp) {
		return new java.sql.Date(timestamp.getTime());
	}

	public static java.sql.Timestamp sqlDateToSqlTimestamp(java.sql.Date date) {
		return new java.sql.Timestamp(date.getTime());
	}

	public static java.sql.Date stringToSqlDate(String dateString) {
		return stringToSqlDate(dateString, DateTimeParser.DATE_PATTERN);
	}

	public static java.sql.Date stringToSqlDate(String dateString, String pattern) {
		return dateToSqlDate(stringToDate(dateString, pattern));
	}

	public static String sqlDateToString(java.sql.Date date) {
		return sqlDateToString(date, DateTimeParser.DATE_PATTERN);
	}

	public static String sqlDateToString(java.sql.Date date, String pattern) {
		return dateToString(sqlDateToDate(date), pattern);
	}

	public static java.sql.Time stringToSqlTime(String dateString) {
		return stringToSqlTime(dateString, DateTimeParser.TIME_PATTERN);
	}

	public static java.sql.Time stringToSqlTime(String dateString, String pattern) {
		return dateToSqlTime(stringToDate(dateString, pattern));
	}

	public static String sqlTimeToString(java.sql.Time time) {
		return sqlTimeToString(time, DateTimeParser.TIME_PATTERN);
	}

	public static String sqlTimeToString(java.sql.Time time, String pattern) {
		return dateToString(sqlTimeToDate(time), pattern);
	}

	public static java.sql.Timestamp stringToSqlTimestamp(String dateString) {
		return stringToSqlTimestamp(dateString, DateTimeParser.DATETIME_PATTERN);
	}

	public static java.sql.Timestamp stringToSqlTimestamp(String dateString, String pattern) {
		return dateToSqlTimestamp(stringToDate(dateString, pattern));
	}

	public static String sqlTimestampToString(java.sql.Timestamp timestamp) {
		return sqlTimestampToString(timestamp, DateTimeParser.DATETIME_PATTERN);
	}

	public static String sqlTimestampToString(java.sql.Timestamp timestamp, String pattern) {
		return dateToString(sqlTimestampToDate(timestamp), pattern);
	}
}
