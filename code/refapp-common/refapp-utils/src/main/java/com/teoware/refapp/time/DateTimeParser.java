package com.teoware.refapp.time;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Parser for converting between {@link DateTime} and a date/time formatted
 * string.
 *
 * @author thomas@teoware.com
 *
 */
public final class DateTimeParser {

    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String TIME_PATTERN = "HH:mm:ss.SSS";
    public static final String DATETIME_PATTERN = DATE_PATTERN + "'T'" + TIME_PATTERN;

    public static DateTime fromString(String dateString, String pattern) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return fromString(dateString, formatter);
    }

    public static String toString(DateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern(pattern);
        return toString(dateTime, formatter);
    }

    public static DateTime fromString(String dateString, DateTimeFormatter formatter) {
        return formatter.parseDateTime(dateString);
    }

    public static String toString(DateTime dateTime, DateTimeFormatter formatter) {
        return formatter.print(dateTime.getMillis());
    }
}
