package com.teoware.refapp.dao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static final String dateMask = "yyyy-MM-dd";
	public static final String timestampMask = "yyyy-MM-dd HH:mm:ss.S";

	public static Date stringToDate(String dateString) throws ParseException {
		return stringToDate(dateString, dateMask);
	}

	public static Date timestampToDate(String timestampString) throws ParseException {
		return stringToDate(timestampString, timestampMask);
	}

	public static Date stringToDate(String dateString, String mask) throws ParseException {
		if (dateString == null) {
			return null;
		}
		return new SimpleDateFormat(mask).parse(dateString);
	}

	public static Calendar stringToCalendar(String dateString) throws ParseException {
		return stringToCalendar(dateString, dateMask);
	}

	public static Calendar timestampToCalendar(String dateString) throws ParseException {
		return stringToCalendar(dateString, timestampMask);
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
		return dateToString(date, dateMask);
	}

	public static String dateToTimestamp(Date date) {
		return dateToString(date, timestampMask);
	}

	public static String dateToString(Date date, String mask) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(mask).format(date);
	}

	public static String calendarToString(Calendar calendar) {
		return dateToString(calendar.getTime(), dateMask);
	}

	public static String calendarToTimestamp(Calendar calendar) {
		return dateToString(calendar.getTime(), timestampMask);
	}
}
