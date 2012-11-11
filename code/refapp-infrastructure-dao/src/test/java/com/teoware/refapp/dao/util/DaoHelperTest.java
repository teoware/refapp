package com.teoware.refapp.dao.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.teoware.refapp.util.time.DateTimeUtils;

public class DaoHelperTest {

	@Test
	public void testIsString() {
		String string = "abcd";
		assertTrue(DaoHelper.isString(string));
	}

	@Test
	public void testIsDate() {
		Date date = DateTimeUtils.stringToDate("2012-01-01");
		assertTrue(DaoHelper.isDate(date));
	}

	@Test
	public void testIsDateWithSqlDate() {
		java.sql.Date date = DateTimeUtils.stringToSqlDate("2012-01-01");
		assertFalse(DaoHelper.isDate(date));
	}

	@Test
	public void testIsDateWithSqlTime() {
		Time time = DateTimeUtils.stringToSqlTime("2012-01-01");
		assertFalse(DaoHelper.isDate(time));
	}

	@Test
	public void testIsDateWithSqlTimestamp() {
		Timestamp timestamp = DateTimeUtils.stringToSqlTimestamp("2012-01-01");
		assertFalse(DaoHelper.isDate(timestamp));
	}

	@Test
	public void testIsCalendar() {
		Calendar calendar = DateTimeUtils.stringToCalendar("2012-01-01");
		assertTrue(DaoHelper.isCalendar(calendar));
	}

	@Test
	public void testIsSqlDate() {
		java.sql.Date date = DateTimeUtils.stringToSqlDate("2012-01-01");
		assertTrue(DaoHelper.isSqlDateTime(date));
	}

	@Test
	public void testIsSqlTime() {
		Time time = DateTimeUtils.stringToSqlTime("2012-01-01");
		assertTrue(DaoHelper.isSqlDateTime(time));
	}

	@Test
	public void testIsSqlTimestamp() {
		Timestamp timestamp = DateTimeUtils.stringToSqlTimestamp("2012-01-01");
		assertTrue(DaoHelper.isSqlDateTime(timestamp));
	}
}