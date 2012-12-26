package com.teoware.refapp.util.time;

import org.joda.time.DateTime;

/**
 * Adapter for converting between {@link DateTime} and a date/time formatted string.
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
