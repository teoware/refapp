package com.teoware.refapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final String DATE_MASK = "yyyy-MM-dd";
	public static final String TIMESTAMP_MASK = "yyyy-MM-dd HH:mm:ss.S";

	public static Date stringToDate(String dateString) throws ParseException {
		return stringToDate(dateString, DATE_MASK);
	}

	public static Date timestampToDate(String timestampString) throws ParseException {
		return stringToDate(timestampString, TIMESTAMP_MASK);
	}

	public static Date stringToDate(String dateString, String mask) throws ParseException {
		if (dateString == null) {
			return null;
		}
		return new SimpleDateFormat(mask).parse(dateString);
	}

	public static Calendar stringToCalendar(String dateString) throws ParseException {
		return stringToCalendar(dateString, DATE_MASK);
	}

	public static Calendar timestampToCalendar(String dateString) throws ParseException {
		return stringToCalendar(dateString, TIMESTAMP_MASK);
	}

	public static Calendar stringToCalendar(String dateString, String mask) throws ParseException {
		if (dateString == null) {
			return null;
		}
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
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(mask).format(date);
	}

	public static String calendarToString(Calendar calendar) {
		return dateToString(calendar.getTime(), DATE_MASK);
	}

	public static String calendarToTimestamp(Calendar calendar) {
		return dateToString(calendar.getTime(), TIMESTAMP_MASK);
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
