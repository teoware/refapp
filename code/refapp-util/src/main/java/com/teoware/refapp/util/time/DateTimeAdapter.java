package com.teoware.refapp.util.time;

import org.joda.time.DateTime;

public class DateTimeAdapter {

	public static DateTime parseDate(String dateString) {
		return DateTimeParser.stringToDateTime(dateString, DateTimeParser.DATE_FORMATTER);
	}

	public static String printDate(DateTime dateTime) {
		return DateTimeParser.dateTimeToString(dateTime, DateTimeParser.DATE_FORMATTER);
	}

	public static DateTime parseTime(String dateString) {
		return DateTimeParser.stringToDateTime(dateString, DateTimeParser.TIME_FORMATTER);
	}

	public static String printTime(DateTime dateTime) {
		return DateTimeParser.dateTimeToString(dateTime, DateTimeParser.TIME_FORMATTER);
	}

	public static DateTime parseDateTime(String dateString) {
		return DateTimeParser.stringToDateTime(dateString, DateTimeParser.DATETIME_FORMATTER);
	}

	public static String printDateTime(DateTime dateTime) {
		return DateTimeParser.dateTimeToString(dateTime, DateTimeParser.DATETIME_FORMATTER);
	}
}
