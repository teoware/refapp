package com.teoware.refapp.util.time;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DateTimeConverterTest {

	private static DateTime dateTime;
	private static Calendar calendar;
	private static int zoneOffset, yyyy, MM, dd, HH, mm, ss, SSS;
	private static String zoneId;

	@BeforeClass
	public static void oneTimeSetUp() {
		zoneOffset = 1;
		zoneId = "Europe/Oslo";
		yyyy = 2010;
		MM = 10;
		dd = 20;
		HH = 12;
		mm = 30;
		ss = 20;
		SSS = 200;
	}

	@Before
	public void setUp() {
		dateTime = new DateTime().withZone(DateTimeZone.forOffsetHours(zoneOffset)).withYear(yyyy).withMonthOfYear(MM)
				.withDayOfMonth(dd).withHourOfDay(HH).withMinuteOfHour(mm).withSecondOfMinute(ss)
				.withMillisOfSecond(SSS);
		calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, yyyy);
		calendar.set(Calendar.MONTH, MM);
		calendar.set(Calendar.DAY_OF_MONTH, dd);
		calendar.set(Calendar.HOUR_OF_DAY, HH);
		calendar.set(Calendar.MINUTE, mm);
		calendar.set(Calendar.SECOND, ss);
		calendar.set(Calendar.MILLISECOND, SSS);
		calendar.setTimeZone(TimeZone.getTimeZone(zoneId));
	}

	@SuppressWarnings("static-access")
	@Test
	public void testFromDateHaveSameMillis() {
		Date date = calendar.getTime();
		DateTime convertedDateTime = new DateTimeConverter().fromDate(date);

		assertEquals(date.getTime(), convertedDateTime.getMillis());
	}

	@Test
	public void testToDateHaveSameMillis() {
		Date convertedDate = DateTimeConverter.toDate(dateTime);

		assertEquals(dateTime.getMillis(), convertedDate.getTime());
	}

	@Test
	public void testFromCalendarHaveSameMillis() {
		DateTime convertedDateTime = DateTimeConverter.fromCalendar(calendar);

		assertEquals(calendar.getTimeInMillis(), convertedDateTime.getMillis());
	}

	@Test
	public void testToCalendarHaveSameMillis() {
		Calendar convertedCalendar = DateTimeConverter.toCalendar(dateTime);

		assertEquals(dateTime.getMillis(), convertedCalendar.getTimeInMillis());
	}

	@Test
	public void testFromGregorianCalendarHaveSameMillis() {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTimeInMillis(calendar.getTimeInMillis());
		DateTime convertedDateTime = DateTimeConverter.fromGregorianCalendar(gregorianCalendar);

		assertEquals(gregorianCalendar.getTimeInMillis(), convertedDateTime.getMillis());
	}

	@Test
	public void testToGregorianCalendarHaveSameMillis() {
		GregorianCalendar convertedGregorianCalendar = DateTimeConverter.toGregorianCalendar(dateTime);

		assertEquals(dateTime.getMillis(), convertedGregorianCalendar.getTimeInMillis());
	}

	@Test
	public void testFromSqlDateHaveSameMillis() {
		java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());
		DateTime convertedDateTime = DateTimeConverter.fromSqlDate(sqlDate);

		assertEquals(sqlDate.getTime(), convertedDateTime.getMillis());
	}

	@Test
	public void testToSqlDateHaveSameMillis() {
		java.sql.Date convertedSqlDate = DateTimeConverter.toSqlDate(dateTime);

		assertEquals(dateTime.getMillis(), convertedSqlDate.getTime());
	}

	@Test
	public void testFromSqlTimeHaveSameMillis() {
		java.sql.Time sqlTime = new java.sql.Time(calendar.getTimeInMillis());
		DateTime convertedDateTime = DateTimeConverter.fromSqlTime(sqlTime);

		assertEquals(sqlTime.getTime(), convertedDateTime.getMillis());
	}

	@Test
	public void testToSqlTimeHaveSameMillis() {
		java.sql.Time convertedSqlTime = DateTimeConverter.toSqlTime(dateTime);

		assertEquals(dateTime.getMillis(), convertedSqlTime.getTime());
	}

	@Test
	public void testFromSqlTimestampHaveSameMillis() {
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(calendar.getTimeInMillis());
		DateTime convertedDateTime = DateTimeConverter.fromSqlTimestamp(sqlTimestamp);

		assertEquals(sqlTimestamp.getTime(), convertedDateTime.getMillis());
	}

	@Test
	public void testToSqlTimestampHaveSameMillis() {
		java.sql.Timestamp convertedSqlTimestamp = DateTimeConverter.toSqlTimestamp(dateTime);

		assertEquals(dateTime.getMillis(), convertedSqlTimestamp.getTime());
	}
}
