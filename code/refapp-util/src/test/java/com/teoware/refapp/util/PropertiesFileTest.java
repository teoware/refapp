package com.teoware.refapp.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.util.PropertiesFile;

public class PropertiesFileTest {

	@BeforeClass
	public static void oneTimeSetUp() {
		
	}

	@AfterClass
	public static void oneTimeTearDown() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadPropertiesFileFromClasspath() throws IOException {
		PropertiesFile properties = PropertiesFile.createFromClasspath("/properties/test.properties");
		String value = properties.get("testKey");
		
		assertNotNull(value);
		assertEquals("testValue", value);
	}
}
