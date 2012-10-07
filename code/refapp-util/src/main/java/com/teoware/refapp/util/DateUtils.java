package com.teoware.refapp.util;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

	public static final String DATE_MASK = "yyyy-MM-dd";
	public static final String TIMESTAMP_MASK = "yyyy-MM-dd HH:mm:ss.S";

	public static Calendar createCalendar(int field, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(field, offset);
		return calendar;
	}

	public static Date createDate(int field, int offset) {
		return createCalendar(field, offset).getTime();
	}

	public static Date stringToDate(String dateString) {
		return stringToDate(dateString, DATE_MASK);
	}

	public static Date stringToDate(String dateString, String mask) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(mask);
		return formatter.parseDateTime(dateString).toDate();
	}

	public static String dateToString(Date date) {
		return dateToString(date, DATE_MASK);
	}

	public static String dateToString(Date date, String mask) {
		return LocalDate.fromDateFields(date).toString(mask);
	}

	public static Calendar stringToCalendar(String dateString) {
		return stringToCalendar(dateString, DATE_MASK);
	}

	public static Calendar stringToCalendar(String dateString, String mask) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(stringToDate(dateString, mask));
		return calendar;
	}

	public static String calendarToString(Calendar calendar) {
		return calendarToString(calendar, DATE_MASK);
	}

	public static String calendarToString(Calendar calendar, String mask) {
		return LocalDate.fromCalendarFields(calendar).toString(mask);
	}

	public static Date timestampToDate(String timestampString) {
		return stringToDate(timestampString, TIMESTAMP_MASK);
	}

	public static String dateToTimestamp(Date date) {
		return dateToString(date, TIMESTAMP_MASK);
	}

	public static Calendar timestampToCalendar(String dateString) {
		return stringToCalendar(dateString, TIMESTAMP_MASK);
	}

	public static String calendarToTimestamp(Calendar calendar) {
		return calendarToString(calendar, TIMESTAMP_MASK);
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
		return stringToSqlDate(dateString, DATE_MASK);
	}

	public static java.sql.Date stringToSqlDate(String dateString, String mask) {
		return dateToSqlDate(stringToDate(dateString, mask));
	}

	public static String sqlDateToString(java.sql.Date date) {
		return sqlDateToString(date, DATE_MASK);
	}

	public static String sqlDateToString(java.sql.Date date, String mask) {
		return dateToString(sqlDateToDate(date), mask);
	}

	public static java.sql.Time stringToSqlTime(String dateString) {
		return stringToSqlTime(dateString, DATE_MASK);
	}

	public static java.sql.Time stringToSqlTime(String dateString, String mask) {
		return dateToSqlTime(stringToDate(dateString, mask));
	}

	public static String sqlTimeToString(java.sql.Time time) {
		return sqlTimeToString(time, DATE_MASK);
	}

	public static String sqlTimeToString(java.sql.Time time, String mask) {
		return dateToString(sqlTimeToDate(time), mask);
	}

	public static java.sql.Timestamp stringToSqlTimestamp(String dateString) {
		return stringToSqlTimestamp(dateString, DATE_MASK);
	}

	public static java.sql.Timestamp stringToSqlTimestamp(String dateString, String mask) {
		return dateToSqlTimestamp(stringToDate(dateString, mask));
	}

	public static String sqlTimestampToString(java.sql.Timestamp timestamp) {
		return sqlTimestampToString(timestamp, DATE_MASK);
	}

	public static String sqlTimestampToString(java.sql.Timestamp timestamp, String mask) {
		return dateToString(sqlTimestampToDate(timestamp), mask);
	}
}
