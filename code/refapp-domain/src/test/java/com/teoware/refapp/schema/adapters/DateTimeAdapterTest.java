package com.teoware.refapp.schema.adapters;

import static junit.framework.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.BeforeClass;
import org.junit.Test;

public class DateTimeAdapterTest {

	private static String parseDateTimeString;
	private static DateTime dateTime;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		parseDateTimeString = "2012-01-01T01:01:01.000+01:00";
		dateTime = new DateTime().withYear(1999).withMonthOfYear(12).withDayOfMonth(12).withHourOfDay(12)
				.withMinuteOfHour(12).withSecondOfMinute(12).withMillisOfSecond(0)
				.withZone(DateTimeZone.forOffsetHours(1));
	}

	@Test
	public void testParseStringSuccessfully() throws Exception {
		DateTime date = DateTimeAdapter.parse(parseDateTimeString);

		assertEquals(2012, date.getYear());
		assertEquals(1, date.getMonthOfYear());
		assertEquals(1, date.getDayOfMonth());
		assertEquals(1, date.getHourOfDay());
		assertEquals(1, date.getMinuteOfHour());
		assertEquals(1, date.getSecondOfMinute());
		assertEquals(0, date.getMillisOfSecond());
	}

	@SuppressWarnings("static-access")
	@Test(expected = IllegalArgumentException.class)
	public void testParseDateStringThrowsIllegalArgumentException() throws Exception {
		new DateTimeAdapter().parse("2012-01-01");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testParseTimeStringThrowsIllegalArgumentException() throws Exception {
		DateTimeAdapter.parse("01:01:01.000+01:00");
	}

	@Test
	public void testPrintDateTimeSuccessfully() throws Exception {
		String dateString = DateTimeAdapter.print(dateTime);

		assertEquals("1999-12-12T12:12:12.000+01:00", dateString);
	}
}
