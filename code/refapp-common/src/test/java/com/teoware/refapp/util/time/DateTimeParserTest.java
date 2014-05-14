package com.teoware.refapp.util.time;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateTimeParserTest {

    private static DateTime dateTime;
    private static String dateString;
    private static String timeString;
    private static String dateTimeString;

    @BeforeClass
    public static void oneTimeSetUp() {
        dateTime = new DateTime().withZone(DateTimeZone.getDefault()).withYear(2000).withMonthOfYear(1).withDayOfMonth(2).withHourOfDay(12).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
        dateString = "2004-05-06";
        timeString = "18:00:00.000";
        dateTimeString = "2004-05-06T18:00:00.000";
    }

    @SuppressWarnings("static-access")
    @Test
    public void testParseDateTimeToStringUsingDateTimePattern() {
        String parsedDateTimeString = new DateTimeParser().toString(dateTime, DateTimeParser.DATETIME_PATTERN);
        String expectedDateTimeString = "2000-01-02T12:00:00.000";

        assertEquals(expectedDateTimeString, parsedDateTimeString);
    }

    @Test
    public void testParseStringToDateTimeUsingDateTimePattern() {
        DateTime parsedDateTime = DateTimeParser.fromString(dateTimeString, DateTimeParser.DATETIME_PATTERN);
        DateTime expectedDateTime = new DateTime().withYear(2004).withMonthOfYear(5).withDayOfMonth(6).withHourOfDay(18).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);

        assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
    }

    @Test
    public void testParseDateTimeToStringUsingDateFormatter() {
        String parsedDateTimeString = DateTimeParser.toString(dateTime, DateTimeParser.DATE_PATTERN);
        String expectedDateTimeString = "2000-01-02";

        assertEquals(expectedDateTimeString, parsedDateTimeString);
    }

    @Test
    public void testParseDateTimeToStringUsingTimeFormatter() {
        String parsedDateTimeString = DateTimeParser.toString(dateTime, DateTimeParser.TIME_PATTERN);
        String expectedDateTimeString = "12:00:00.000";

        assertEquals(expectedDateTimeString, parsedDateTimeString);
    }

    @Test
    public void testParseDateTimeToStringUsingDateTimeFormatter() {
        String parsedDateTimeString = DateTimeParser.toString(dateTime, DateTimeParser.DATETIME_PATTERN);
        String expectedDateTimeString = "2000-01-02T12:00:00.000";

        assertEquals(expectedDateTimeString, parsedDateTimeString);
    }

    @Test
    public void testParseStringToDateTimeUsingDateFormatter() {
        DateTime parsedDateTime = DateTimeParser.fromString(dateString, DateTimeParser.DATE_PATTERN);
        DateTime expectedDateTime = new DateTime().withYear(2004).withMonthOfYear(5).withDayOfMonth(6).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);

        assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
    }

    @Test
    public void testParseStringToDateTimeUsingTimeFormatter() {
        DateTime parsedDateTime = DateTimeParser.fromString(timeString, DateTimeParser.TIME_PATTERN);
        DateTime expectedDateTime = new DateTime().withYear(1970).withMonthOfYear(1).withDayOfMonth(1).withHourOfDay(18).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);

        assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
    }

    @Test
    public void testParseStringToDateTimeUsingDateTimeFormatter() {
        DateTime parsedDateTime = DateTimeParser.fromString(dateString + "T" + timeString, DateTimeParser.DATETIME_PATTERN);
        DateTime expectedDateTime = new DateTime().withYear(2004).withMonthOfYear(5).withDayOfMonth(6).withHourOfDay(18).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);

        assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
    }
}
