package com.teoware.refapp.schema.adapters;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

public class DateAdapterTest {

	private static String dateString;
	private static DateTime date;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		dateString = "2012-01-01";
		date = new DateTime().withYear(1999).withMonthOfYear(12).withDayOfMonth(12).withHourOfDay(12)
				.withMinuteOfHour(12).withSecondOfMinute(12);
	}

	@Test
	public void testParseStringSuccessfully() throws Exception {
		DateTime date = DateAdapter.parse(dateString);

		assertEquals(2012, date.getYear());
		assertEquals(1, date.getMonthOfYear());
		assertEquals(1, date.getDayOfMonth());
	}

	@Test
	public void testParseDateTimeStringThrowsIllegalArgumentException() throws Exception {
		try {
			DateAdapter.parse("2012-01-01T01:01:01.000+01:00");

			fail("Should throw exception");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testParseTimeStringThrowsIllegalArgumentException() throws Exception {
		try {
			DateAdapter.parse("01:01:01.000+01:00");

			fail("Should throw exception");
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testPrintDateTimeSuccessfully() throws Exception {
		String dateString = DateAdapter.print(date);

		assertEquals("1999-12-12", dateString);
	}
}
