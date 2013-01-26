package com.teoware.refapp.util.time;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

public class DateTimeUtilsTest {

	@SuppressWarnings("static-access")
	@Test
	public void testCreateCalendarWithDayOffset() {
		Calendar cal = new DateTimeUtils().createCalendar(Calendar.DAY_OF_YEAR, 5);

		assertEquals(dayPlus5(), cal.get(Calendar.DAY_OF_YEAR));
	}

	@Test
	public void testCreateCalendarWithHourOffset() {
		Calendar cal = DateTimeUtils.createCalendar(Calendar.HOUR_OF_DAY, -5);

		assertEquals(hourMinus5(), cal.get(Calendar.HOUR_OF_DAY));
	}

	@Test
	public void testCreateDateWithDayOffset() {
		Date date = DateTimeUtils.createDate(Calendar.DAY_OF_YEAR, 5);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		assertEquals(dayPlus5(), cal.get(Calendar.DAY_OF_YEAR));
	}

	@Test
	public void testCreateDateWithHourOffset() {
		Date date = DateTimeUtils.createDate(Calendar.HOUR_OF_DAY, -5);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		assertEquals(hourMinus5(), cal.get(Calendar.HOUR_OF_DAY));
	}

	private int dayPlus5() {
		Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.DAY_OF_YEAR, 5);
		return cal.get(Calendar.DAY_OF_YEAR);
	}

	private int hourMinus5() {
		Calendar cal = Calendar.getInstance();
		cal.roll(Calendar.HOUR_OF_DAY, -5);
		return cal.get(Calendar.HOUR_OF_DAY);
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

		String str = DateTimeUtils.dateToString(cal.getTime());

		assertEquals("1970-01-01", str);
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

		String str = DateTimeUtils.calendarToString(cal);

		assertEquals("1970-01-01", str);
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
		cal.set(1970, Calendar.JANUARY, 1, 12, 11, 10);
		cal.set(Calendar.MILLISECOND, 123);

		String str = DateTimeUtils.dateToTimestamp(cal.getTime());

		assertEquals("1970-01-01 12:11:10.123", str);
	}

	@Test
	public void testTimestampToCalendar() {
		Calendar cal = DateTimeUtils.timestampToCalendar("1970-01-01 06:07:08.999");

		assertEquals(1970, cal.get(Calendar.YEAR));
		assertEquals(Calendar.JANUARY, cal.get(Calendar.MONTH));
		assertEquals(1, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(6, cal.get(Calendar.HOUR_OF_DAY));
		assertEquals(7, cal.get(Calendar.MINUTE));
		assertEquals(8, cal.get(Calendar.SECOND));
		assertEquals(999, cal.get(Calendar.MILLISECOND));
	}

	@Test
	public void testCalendarToTimestamp() {
		Calendar cal = Calendar.getInstance();
		cal.set(1970, Calendar.JANUARY, 1, 3, 10, 0);
		cal.set(Calendar.MILLISECOND, 101);

		String str = DateTimeUtils.calendarToTimestamp(cal);

		assertEquals("1970-01-01 03:10:00.101", str);
	}

	@Test
	public void testDateToSqlDate() {
		Date date = new Date();
		java.sql.Date sqlDate = DateTimeUtils.dateToSqlDate(date);

		assertEquals(date.getTime(), sqlDate.getTime());
	}

	@Test
	public void testSqlDateToDate() {
		java.sql.Date sqlDate = new java.sql.Date(0);
		Date date = DateTimeUtils.sqlDateToDate(sqlDate);

		assertEquals(sqlDate.getTime(), date.getTime());
	}

	@Test
	public void testDateToSqlTime() {
		Date date = new Date();
		java.sql.Time sqlTime = DateTimeUtils.dateToSqlTime(date);

		assertEquals(date.getTime(), sqlTime.getTime());
	}

	@Test
	public void testSqlTimeToDate() {
		java.sql.Time sqlTime = new java.sql.Time(0);
		Date date = DateTimeUtils.sqlTimeToDate(sqlTime);

		assertEquals(sqlTime.getTime(), date.getTime());
	}

	@Test
	public void testDateToSqlTimestamp() {
		Date date = new Date();
		java.sql.Timestamp sqlTimestamp = DateTimeUtils.dateToSqlTimestamp(date);

		assertEquals(date.getTime(), sqlTimestamp.getTime());
	}

	@Test
	public void testSqlTimestampToDate() {
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(0);
		Date date = DateTimeUtils.sqlTimestampToDate(sqlTimestamp);

		assertEquals(sqlTimestamp.getTime(), date.getTime());
	}

	@Test
	public void testCalendarToSqlDate() {
		Calendar cal = Calendar.getInstance();
		java.sql.Date sqlDate = DateTimeUtils.calendarToSqlDate(cal);

		assertEquals(cal.getTimeInMillis(), sqlDate.getTime());
	}

	@Test
	public void testSqlDateToCalendar() {
		java.sql.Date sqlDate = new java.sql.Date(0);
		Calendar cal = DateTimeUtils.sqlDateToCalendar(sqlDate);

		assertEquals(sqlDate.getTime(), cal.getTimeInMillis());
	}

	@Test
	public void testCalendarToSqlTime() {
		Calendar cal = Calendar.getInstance();
		java.sql.Time sqlTime = DateTimeUtils.calendarToSqlTime(cal);

		assertEquals(cal.getTimeInMillis(), sqlTime.getTime());
	}

	@Test
	public void testSqlTimeToCalendar() {
		java.sql.Time sqlTime = new java.sql.Time(0);
		Calendar cal = DateTimeUtils.sqlTimeToCalendar(sqlTime);

		assertEquals(sqlTime.getTime(), cal.getTimeInMillis());
	}

	@Test
	public void testCalendarToSqlTimestamp() {
		Calendar cal = Calendar.getInstance();
		java.sql.Timestamp sqlTimestamp = DateTimeUtils.calendarToSqlTimestamp(cal);

		assertEquals(cal.getTimeInMillis(), sqlTimestamp.getTime());
	}

	@Test
	public void testSqlTimestampToCalendar() {
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(0);
		Calendar cal = DateTimeUtils.sqlTimestampToCalendar(sqlTimestamp);

		assertEquals(sqlTimestamp.getTime(), cal.getTimeInMillis());
	}

	@Test
	public void testSqlTimestampToSqlDate() {
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(0);
		java.sql.Date sqlDate = DateTimeUtils.sqlTimestampToSqlDate(sqlTimestamp);

		assertEquals(sqlTimestamp.getTime(), sqlDate.getTime());
	}

	@Test
	public void testSqlDateToSqlTimestamp() {
		java.sql.Date sqlDate = new java.sql.Date(0);
		java.sql.Timestamp sqlTimestamp = DateTimeUtils.sqlDateToSqlTimestamp(sqlDate);

		assertEquals(sqlDate.getTime(), sqlTimestamp.getTime());
	}

	@Test
	public void testStringToSqlDate() {
		java.sql.Date sqlDate = new java.sql.Date(offsetInMillis());
		java.sql.Date sqlDate2 = DateTimeUtils.stringToSqlDate("1970-01-01");

		assertEquals(sqlDate.getTime(), sqlDate2.getTime());
	}

	@Test
	public void testSqlDateToString() {
		java.sql.Date sqlDate = new java.sql.Date(0);
		String str = DateTimeUtils.sqlDateToString(sqlDate);

		assertEquals("1970-01-01", str);
	}

	@Test
	public void testStringToSqlTime() {
		java.sql.Time sqlTime = new java.sql.Time(offsetInMillis());
		java.sql.Time sqlTime2 = DateTimeUtils.stringToSqlTime("00:00:00");

		assertEquals(sqlTime.getTime(), sqlTime2.getTime());
	}

	@Test
	public void testSqlTimeToString() {
		java.sql.Time sqlTime = new java.sql.Time(offsetInMillis());
		String str = DateTimeUtils.sqlTimeToString(sqlTime);

		assertEquals("00:00:00", str);
	}

	@Test
	public void testStringToSqlTimestamp() {
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(offsetInMillis());
		java.sql.Timestamp sqlTimestamp2 = DateTimeUtils.stringToSqlTimestamp("1970-01-01 00:00:00.000");

		assertEquals(sqlTimestamp.getTime(), sqlTimestamp2.getTime());
	}

	@Test
	public void testSqlTimestampToString() {
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(offsetInMillis());
		String str = DateTimeUtils.sqlTimestampToString(sqlTimestamp);

		assertEquals("1970-01-01 00:00:00.000", str);
	}

	private int offsetInMillis() {
		int offset = DateTimeZone.UTC.getOffset(0) - DateTime.now().getZone().getOffset(0);
		return offset;
	}
}
