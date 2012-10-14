package com.teoware.refapp.util.time;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.BeforeClass;
import org.junit.Test;

public class DateTimeParserTest {

	private static DateTime dateTime;
	private static String dateString;
	private static String timeString;
	private static String dateTimeString;
	private static String dateTimeMillisString;
	private static String dateTimeZoneString;
	private static String dateTimeFullString;

	@BeforeClass
	public static void oneTimeSetUp() {
		dateTime = new DateTime().withZone(DateTimeZone.forOffsetHours(1)).withYear(2000).withMonthOfYear(1)
				.withDayOfMonth(2).withHourOfDay(12).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);
		dateString = "2004-05-06";
		timeString = "18:00:00.000+01";
		dateTimeString = "2004-05-06T18:00:00";
		dateTimeMillisString = dateTimeString + ".000";
		dateTimeZoneString = dateTimeString + "+0100";
		dateTimeFullString = dateTimeString + ".000+0100";
	}

	@Test
	public void testParseDateTimeToStringUsingDateTimePattern() {
		String parsedDateTimeString = DateTimeParser.dateTimeToString(dateTime, DateTimeParser.DATETIME_PATTERN);
		String expectedDateTimeString = "2000-01-02T12:00:00";

		assertEquals(expectedDateTimeString, parsedDateTimeString);
	}

	@Test
	public void testParseDateTimeToStringUsingDateTimeMillisPattern() {
		String parsedDateTimeString = DateTimeParser.dateTimeToString(dateTime, DateTimeParser.DATETIME_MILLIS_PATTERN);
		String expectedDateTimeString = "2000-01-02T12:00:00.000";

		assertEquals(expectedDateTimeString, parsedDateTimeString);
	}

	@Test
	public void testParseDateTimeToStringUsingDateTimeZonePattern() {
		String parsedDateTimeString = DateTimeParser.dateTimeToString(dateTime, DateTimeParser.DATETIME_ZONE_PATTERN);
		String expectedDateTimeString = "2000-01-02T12:00:00+0100";

		assertEquals(expectedDateTimeString, parsedDateTimeString);
	}

	@Test
	public void testParseDateTimeToStringUsingDateTimeFullPattern() {
		String parsedDateTimeString = DateTimeParser.dateTimeToString(dateTime, DateTimeParser.DATETIME_FULL_PATTERN);
		String expectedDateTimeString = "2000-01-02T12:00:00.000+0100";

		assertEquals(expectedDateTimeString, parsedDateTimeString);
	}

	@Test
	public void testParseStringToDateTimeUsingDateTimePattern() {
		DateTime parsedDateTime = DateTimeParser.stringToDateTime(dateTimeString, DateTimeParser.DATETIME_PATTERN);
		DateTime expectedDateTime = new DateTime().withYear(2004).withMonthOfYear(5).withDayOfMonth(6)
				.withHourOfDay(18).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);

		assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
	}

	@Test
	public void testParseStringToDateTimeUsingDateTimeMillisPattern() {
		DateTime parsedDateTime = DateTimeParser.stringToDateTime(dateTimeMillisString,
				DateTimeParser.DATETIME_MILLIS_PATTERN);
		DateTime expectedDateTime = new DateTime().withYear(2004).withMonthOfYear(5).withDayOfMonth(6)
				.withHourOfDay(18).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);

		assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
	}

	@Test
	public void testParseStringToDateTimeUsingDateTimeZonePattern() {
		DateTime parsedDateTime = DateTimeParser.stringToDateTime(dateTimeZoneString,
				DateTimeParser.DATETIME_ZONE_PATTERN);
		DateTime expectedDateTime = new DateTime().withZone(DateTimeZone.forOffsetHours(1)).withYear(2004)
				.withMonthOfYear(5).withDayOfMonth(6).withHourOfDay(18).withMinuteOfHour(0).withSecondOfMinute(0)
				.withMillisOfSecond(0);

		assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
	}

	@Test
	public void testParseStringToDateTimeUsingDateTimeFullPattern() {
		DateTime parsedDateTime = DateTimeParser.stringToDateTime(dateTimeFullString,
				DateTimeParser.DATETIME_FULL_PATTERN);
		DateTime expectedDateTime = new DateTime().withZone(DateTimeZone.forOffsetHours(1)).withYear(2004)
				.withMonthOfYear(5).withDayOfMonth(6).withHourOfDay(18).withMinuteOfHour(0).withSecondOfMinute(0)
				.withMillisOfSecond(0);

		assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
	}

	@Test
	public void testParseDateTimeToStringUsingDateFormatter() {
		String parsedDateTimeString = DateTimeParser.dateTimeToString(dateTime, DateTimeParser.DATE_FORMATTER);
		String expectedDateTimeString = "2000-01-02";

		assertEquals(expectedDateTimeString, parsedDateTimeString);
	}

	@Test
	public void testParseDateTimeToStringUsingTimeFormatter() {
		String parsedDateTimeString = DateTimeParser.dateTimeToString(dateTime, DateTimeParser.TIME_FORMATTER);
		String expectedDateTimeString = "12:00:00.000+01:00";

		assertEquals(expectedDateTimeString, parsedDateTimeString);
	}

	@Test
	public void testParseDateTimeToStringUsingDateTimeFormatter() {
		String parsedDateTimeString = DateTimeParser.dateTimeToString(dateTime, DateTimeParser.DATETIME_FORMATTER);
		String expectedDateTimeString = "2000-01-02T12:00:00.000+01:00";

		assertEquals(expectedDateTimeString, parsedDateTimeString);
	}

	@Test
	public void testParseStringToDateTimeUsingDateFormatter() {
		DateTime parsedDateTime = DateTimeParser.stringToDateTime(dateString, DateTimeParser.DATE_FORMATTER);
		DateTime expectedDateTime = new DateTime().withZone(DateTimeZone.forOffsetHours(2)).withYear(2004)
				.withMonthOfYear(5).withDayOfMonth(6).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
				.withMillisOfSecond(0);

		assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
	}

	@Test
	public void testParseStringToDateTimeUsingTimeFormatter() {
		DateTime parsedDateTime = DateTimeParser.stringToDateTime(timeString, DateTimeParser.TIME_FORMATTER);
		DateTime expectedDateTime = new DateTime().withYear(1970).withMonthOfYear(1).withDayOfMonth(1)
				.withHourOfDay(18).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0);

		assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
	}

	@Test
	public void testParseStringToDateTimeUsingDateTimeFormatter() {
		DateTime parsedDateTime = DateTimeParser.stringToDateTime(dateString + "T" + timeString,
				DateTimeParser.DATETIME_FORMATTER);
		DateTime expectedDateTime = new DateTime().withZone(DateTimeZone.forOffsetHours(1)).withYear(2004)
				.withMonthOfYear(5).withDayOfMonth(6).withHourOfDay(18).withMinuteOfHour(0).withSecondOfMinute(0)
				.withMillisOfSecond(0);

		assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
	}
}
