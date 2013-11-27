package com.teoware.refapp.util.time;

import org.joda.time.DateTime;

/**
 * Adapter for converting between {@link DateTime} and a date/time formatted
 * string.
 * <p>
 * For use with JAX-WS and JAXB projects.
 * <p>
 * Uses {@link DateTimeParser} to do the actual conversion.
 * 
 * @author thomas@teoware.com
 * 
 */
public class DateTimeAdapter {

	public static DateTime parseDate(String dateString) {
		return DateTimeParser.fromString(dateString, DateTimeParser.DATE_PATTERN);
	}

	public static String printDate(DateTime dateTime) {
		return DateTimeParser.toString(dateTime, DateTimeParser.DATE_PATTERN);
	}

	public static DateTime parseTime(String dateString) {
		return DateTimeParser.fromString(dateString, DateTimeParser.TIME_PATTERN);
	}

	public static String printTime(DateTime dateTime) {
		return DateTimeParser.toString(dateTime, DateTimeParser.TIME_PATTERN);
	}

	public static DateTime parseDateTime(String dateString) {
		return DateTimeParser.fromString(dateString, DateTimeParser.DATETIME_PATTERN);
	}

	public static String printDateTime(DateTime dateTime) {
		return DateTimeParser.toString(dateTime, DateTimeParser.DATETIME_PATTERN);
	}
}
