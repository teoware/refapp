package com.teoware.refapp.dao.util;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.junit.Test;

import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.enums.Status;

public class MapperHelperTest {

	@SuppressWarnings("static-access")
	@Test
	public void testMapGender() {
		assertEquals(Gender.MALE, new MapperHelper().mapGender("MALE"));
		assertEquals(Gender.FEMALE, MapperHelper.mapGender("FEMALE"));
	}

	@Test
	public void testMapStatus() {
		assertEquals(Status.PENDING, MapperHelper.mapStatus("PENDING"));
		assertEquals(Status.ACTIVE, MapperHelper.mapStatus("ACTIVE"));
		assertEquals(Status.SUSPENDED, MapperHelper.mapStatus("SUSPENDED"));
		assertEquals(Status.DELETED, MapperHelper.mapStatus("DELETED"));
	}

	@Test
	public void testMapDate() {
		assertEquals(0, MapperHelper.mapDate(new Date(0)).getMillis());
		assertEquals(10, MapperHelper.mapDate(new Date(10)).getMillis());
	}

	@Test
	public void testMapTime() {
		assertEquals(100, MapperHelper.mapTime(new Time(100)).getMillis());
		assertEquals(1000, MapperHelper.mapTime(new Time(1000)).getMillis());
	}

	@Test
	public void testMapTimestamp() {
		assertEquals(10000, MapperHelper.mapTimestamp(new Timestamp(10000)).getMillis());
		assertEquals(100000, MapperHelper.mapTimestamp(new Timestamp(100000)).getMillis());
	}
}
