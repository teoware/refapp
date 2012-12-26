package com.teoware.refapp.util.time;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

public class DateTimeAdapterTest {

	@SuppressWarnings("static-access")
	@Test
	public void testParseDate() {
		String dateString = "2004-05-06";
		DateTime parsedDateTime = new DateTimeAdapter().parseDate(dateString);
		DateTime expectedDateTime = new DateTime().withZone(DateTimeZone.getDefault()).withYear(2004)
				.withMonthOfYear(5).withDayOfMonth(6).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)
				.withMillisOfSecond(0);

		assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
	}

	@Test
	public void testPrintDate() {
		DateTime dateTime = new DateTime().withYear(2004).withMonthOfYear(5).withDayOfMonth(6);
		String parcedDateString = DateTimeAdapter.printDate(dateTime);
		String expectedDateString = "2004-05-06";

		assertEquals(expectedDateString, parcedDateString);
	}

	@Test
	public void testParseTime() {
		String dateString = "12:03:20.900+01:00";
		DateTime parsedDateTime = DateTimeAdapter.parseTime(dateString);
		DateTime expectedDateTime = new DateTime().withZone(DateTimeZone.forOffsetHours(1)).withYear(1970)
				.withMonthOfYear(1).withDayOfMonth(1).withHourOfDay(12).withMinuteOfHour(3).withSecondOfMinute(20)
				.withMillisOfSecond(900);

		assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
	}

	@Test
	public void testPrintTime() {
		DateTime dateTime = new DateTime().withZone(DateTimeZone.forOffsetHours(1)).withYear(1970).withMonthOfYear(1)
				.withDayOfMonth(1).withHourOfDay(12).withMinuteOfHour(3).withSecondOfMinute(20).withMillisOfSecond(900);
		String parcedTimeString = DateTimeAdapter.printTime(dateTime);
		String expectedTimeString = "12:03:20.900+01:00";

		assertEquals(expectedTimeString, parcedTimeString);
	}

	@Test
	public void testParseDateTime() {
		String dateTimeString = "2004-05-06T12:03:20.900+01:00";
		DateTime parsedDateTime = DateTimeAdapter.parseDateTime(dateTimeString);
		DateTime expectedDateTime = new DateTime().withZone(DateTimeZone.forOffsetHours(1)).withYear(2004)
				.withMonthOfYear(5).withDayOfMonth(6).withHourOfDay(12).withMinuteOfHour(3).withSecondOfMinute(20)
				.withMillisOfSecond(900);

		assertEquals(expectedDateTime.getMillis(), parsedDateTime.getMillis());
	}

	@Test
	public void testPrintDateTime() {
		DateTime dateTime = new DateTime().withZone(DateTimeZone.forOffsetHours(1)).withYear(2010).withMonthOfYear(1)
				.withDayOfMonth(24).withHourOfDay(9).withMinuteOfHour(44).withSecondOfMinute(51)
				.withMillisOfSecond(232);
		String parcedDateString = DateTimeAdapter.printDateTime(dateTime);
		String expectedDateString = "2010-01-24T09:44:51.232+01:00";

		assertEquals(expectedDateString, parcedDateString);
	}
}
