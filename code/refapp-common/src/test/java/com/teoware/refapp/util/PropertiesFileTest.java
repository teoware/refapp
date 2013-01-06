package com.teoware.refapp.util;

import static java.io.File.separator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class PropertiesFileTest {

	private static PropertiesFile properties;

	@BeforeClass
	public static void oneTimeSetUp() throws IOException {
		properties = PropertiesFile.createFromClasspath("/properties/test.properties");
	}

	@Test
	public void testCreateFromFile() throws IOException {
		assertNotNull(PropertiesFile.createFromFile(
				"src" + separator + "test" + separator + "resources" + separator + "properties" + separator
						+ "test.properties").get("stringKey"));
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testCreateFromFileNoFileFound() throws IOException {
		assertNotNull(PropertiesFile.createFromFile(
				"src" + separator + "test" + separator + "resources" + separator + "properties" + separator
						+ "notfound.properties").get("stringKey"));
	}

	@Test
	public void testCreateFromClasspath() throws IOException {
		assertNotNull(PropertiesFile.createFromClasspath("/properties/test.properties").get("stringKey"));
	}

	@Test
	public void testCreateUsingClassLoader() throws IOException {
		// TODO assertNotNull(PropertiesFile.createUsingClassLoader("test.properties").get("stringKey"));
	}

	@Test
	public void testStringValue() {
		String value = properties.get("stringKey");

		assertNotNull(value);
		assertEquals("testValue", value);
	}

	@Test
	public void testIntegerValue() {
		int value = properties.getInteger("intKey");

		assertNotNull(value);
		assertEquals(7, value);
	}

	@Test
	public void testLongValue() {
		long value = properties.getLong("longKey");

		assertNotNull(value);
		assertEquals(8L, value);
	}

	@Test
	public void testFloatValue() {
		float value = properties.getFloat("floatKey");

		assertNotNull(value);
		assertEquals(0.7F, value, 0.01);
	}

	@Test
	public void testDoubleValue() {
		double value = properties.getDouble("doubleKey");

		assertNotNull(value);
		assertEquals(0.8D, value, 0.01);
	}

	@Test
	public void testBooleanValue() {
		boolean value = properties.getBoolean("booleanKey");

		assertNotNull(value);
		assertTrue(value);
	}

	@Test
	public void testVar1Value() {
		String value = properties.get("varKey1");

		assertNotNull(value);
		assertEquals("hey there", value);
	}

	@Test
	public void testVar2Value() {
		String value = properties.get("varKey2");

		assertNotNull(value);
		assertEquals("hey there mr.", value);
	}
}
