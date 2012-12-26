package com.teoware.refapp.dao.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DaoHelperTest {

	@Test
	public void testIsString() {
		String string = "abcd";
		assertTrue(DaoHelper.isString(string));
	}

	@Test
	public void testIsDate() {
		Date date = new Date();
		assertTrue(DaoHelper.isDate(date));
	}

	@Test
	public void testIsDateWithSqlDate() {
		java.sql.Date date = new java.sql.Date(0);
		assertFalse(DaoHelper.isDate(date));
	}

	@Test
	public void testIsDateWithSqlTime() {
		Time time = new Time(0);
		assertFalse(DaoHelper.isDate(time));
	}

	@Test
	public void testIsDateWithSqlTimestamp() {
		Timestamp timestamp = new Timestamp(0);
		assertFalse(DaoHelper.isDate(timestamp));
	}

	@Test
	public void testIsCalendar() {
		Calendar calendar = Calendar.getInstance();
		assertTrue(DaoHelper.isCalendar(calendar));
	}

	@Test
	public void testIsSqlDate() {
		java.sql.Date date = new java.sql.Date(0);
		assertTrue(DaoHelper.isSqlDateTime(date));
	}

	@Test
	public void testIsSqlTime() {
		Time time = new Time(0);
		assertTrue(DaoHelper.isSqlDateTime(time));
	}

	@Test
	public void testIsSqlTimestamp() {
		Timestamp timestamp = new Timestamp(0);
		assertTrue(DaoHelper.isSqlDateTime(timestamp));
	}
}