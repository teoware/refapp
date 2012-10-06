package com.teoware.refapp.util;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

	public static final String DATE_MASK = "yyyy-MM-dd";
	public static final String TIMESTAMP_MASK = "yyyy-MM-dd HH:mm:ss.S";

	public static Date stringToDate(String dateString) {
		return stringToDate(dateString, DATE_MASK);
	}

	public static Date timestampToDate(String timestampString) {
		return stringToDate(timestampString, TIMESTAMP_MASK);
	}

	public static Date stringToDate(String dateString, String mask) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(mask);
		return formatter.parseDateTime(dateString).toDate();
	}

	public static Calendar stringToCalendar(String dateString) {
		return stringToCalendar(dateString, DATE_MASK);
	}

	public static Calendar timestampToCalendar(String dateString) {
		return stringToCalendar(dateString, TIMESTAMP_MASK);
	}

	public static Calendar stringToCalendar(String dateString, String mask) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(stringToDate(dateString, mask));
		return calendar;
	}

	public static String dateToString(Date date) {
		return dateToString(date, DATE_MASK);
	}

	public static String dateToTimestamp(Date date) {
		return dateToString(date, TIMESTAMP_MASK);
	}

	public static String dateToString(Date date, String mask) {
		return LocalDate.fromDateFields(date).toString(mask);
	}

	public static String calendarToString(Calendar calendar) {
		return calendarToString(calendar, DATE_MASK);
	}

	public static String calendarToTimestamp(Calendar calendar) {
		return calendarToString(calendar, TIMESTAMP_MASK);
	}

	public static String calendarToString(Calendar calendar, String mask) {
		return LocalDate.fromCalendarFields(calendar).toString(mask);
	}

	public static Calendar createCalendar(int field, int offset) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(field, offset);
		return calendar;
	}

	public static Date createDate(int field, int amount) {
		return createCalendar(field, amount).getTime();
	}

	public static java.sql.Date calendarToSqlDate(Calendar calendar) {
		return new java.sql.Date(calendar.getTimeInMillis());
	}

	public static java.sql.Timestamp calendarToSqlTimestamp(Calendar calendar) {
		return new java.sql.Timestamp(calendar.getTimeInMillis());
	}

	public static java.sql.Date dateToSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public static java.sql.Timestamp dateToSqlTimestamp(Date date) {
		return new java.sql.Timestamp(date.getTime());
	}
}
