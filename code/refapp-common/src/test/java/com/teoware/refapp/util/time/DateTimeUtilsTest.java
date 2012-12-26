package com.teoware.refapp.util.time;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateTimeUtilsTest {

	@Test
	public void testCreateCalendarWithDayOffset() {
		Calendar cal = DateTimeUtils.createCalendar(Calendar.DAY_OF_YEAR, 5);

		assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_YEAR) + 5, cal.get(Calendar.DAY_OF_YEAR));
	}

	@Test
	public void testCreateCalendarWithHourOffset() {
		Calendar cal = DateTimeUtils.createCalendar(Calendar.HOUR_OF_DAY, -5);

		assertEquals(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) - 5, cal.get(Calendar.HOUR_OF_DAY));
	}

	@Test
	public void testCreateDateWithDayOffset() {
		Date date = DateTimeUtils.createDate(Calendar.DAY_OF_YEAR, 5);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		assertEquals(Calendar.getInstance().get(Calendar.DAY_OF_YEAR) + 5, cal.get(Calendar.DAY_OF_YEAR));
	}

	@Test
	public void testCreateDateWithHourOffset() {
		Date date = DateTimeUtils.createDate(Calendar.HOUR_OF_DAY, -5);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		assertEquals(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) - 5, cal.get(Calendar.HOUR_OF_DAY));
	}

	@Test
	public void testStringToDate() {
		Date date = DateTimeUtils.stringToDate("1970-01-01");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		assertEquals(1970, cal.get(Calendar.YEAR));
		assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
		assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, cal.get(Calendar.MINUTE));
		assertEquals(0, cal.get(Calendar.SECOND));
		assertEquals(0, cal.get(Calendar.MILLISECOND));
	}

	@Test
	public void testDateToString() {
		Calendar cal = Calendar.getInstance();
		cal.set(1970, Calendar.JANUARY, 1);

		String date = DateTimeUtils.dateToString(cal.getTime());

		assertEquals("1970-01-01", date);
	}
	
	@Test
	public void testStringToCalendar() {
		Calendar cal = DateTimeUtils.stringToCalendar("1970-01-01");

		assertEquals(1970, cal.get(Calendar.YEAR));
		assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
		assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(0, cal.get(Calendar.HOUR_OF_DAY));
		assertEquals(0, cal.get(Calendar.MINUTE));
		assertEquals(0, cal.get(Calendar.SECOND));
		assertEquals(0, cal.get(Calendar.MILLISECOND));
	}

	@Test
	public void testCalendarToString() {
		Calendar cal = Calendar.getInstance();
		cal.set(1970, Calendar.JANUARY, 1);

		String date = DateTimeUtils.calendarToString(cal);

		assertEquals("1970-01-01", date);
	}
	
	@Test
	public void testTimestampToDate() {
		Date date = DateTimeUtils.timestampToDate("1970-01-01 12:13:14.500");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		assertEquals(1970, cal.get(Calendar.YEAR));
		assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
		assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(12, cal.get(Calendar.HOUR_OF_DAY));
		assertEquals(13, cal.get(Calendar.MINUTE));
		assertEquals(14, cal.get(Calendar.SECOND));
		assertEquals(500, cal.get(Calendar.MILLISECOND));
	}

	@Test
	public void testDateToTimestamp() {
		Calendar cal = Calendar.getInstance();
		cal.set(1970, Calendar.JANUARY, 1);

		String date = DateTimeUtils.dateToString(cal.getTime());

		assertEquals("1970-01-01", date);
	}
	
	@Test
	public void testTimestampToCalendar() {
		Calendar cal = DateTimeUtils.stringToCalendar("1970-01-01");

		assertEquals(1970, cal.get(Calendar.YEAR));
		assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
		assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testCalendarToTimestamp() {
		Calendar cal = Calendar.getInstance();
		cal.set(1970, Calendar.JANUARY, 1);

		String date = DateTimeUtils.calendarToString(cal);

		assertEquals("1970-01-01", date);
	}
}
