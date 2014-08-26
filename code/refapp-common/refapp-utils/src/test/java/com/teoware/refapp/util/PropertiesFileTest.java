package com.teoware.refapp.util;

import static java.io.File.separator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.teoware.refapp.properties.PropertiesFile;
import org.junit.BeforeClass;
import org.junit.Test;

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
                + "test.properties").getString("stringKey"));
    }

    @Test(expected = FileNotFoundException.class)
    public void testCreateFromFileNoFileFound() throws IOException {
        assertNotNull(PropertiesFile.createFromFile(
                "src" + separator + "test" + separator + "resources" + separator + "properties" + separator
                + "notfound.properties").getString("stringKey"));
    }

    @Test
    public void testCreateFromClasspath() throws IOException {
        assertNotNull(PropertiesFile.createFromClasspath("/properties/test.properties").getString("stringKey"));
    }

    @Test
    public void testCreateUsingClassLoader() throws IOException {
        // TODO assertNotNull(PropertiesFile.createUsingClassLoader("test.properties").get("stringKey"));
    }

    @Test
    public void testStringValue() {
        String value = properties.getString("stringKey");

        assertNotNull(value);
        assertEquals("testValue", value);
    }

    @Test
    public void testStringAndDefaultValue() {
        String value = properties.getString("stringKey", "whatever");

        assertNotNull(value);
        assertEquals("testValue", value);
    }

    @Test
    public void testMissingStringValueButDefaultValue() {
        String value = properties.getString("missingKey", "whatever");

        assertNotNull(value);
        assertEquals("whatever", value);
    }

    @Test
    public void testIntegerValue() {
        int value = properties.getInteger("intKey");

        assertNotNull(value);
        assertEquals(7, value);
    }

    @Test
    public void testIntegerValueAndDefaultValue() {
        int value = properties.getInteger("intKey", 99);

        assertNotNull(value);
        assertEquals(7, value);
    }

    @Test
    public void testMissingIntegerValueButDefaultValue() {
        int value = properties.getInteger("missingKey", 99);

        assertNotNull(value);
        assertEquals(99, value);
    }

    @Test
    public void testLongValue() {
        long value = properties.getLong("longKey");

        assertNotNull(value);
        assertEquals(8L, value);
    }

    @Test
    public void testLongValueAndDefaultValue() {
        long value = properties.getLong("longKey", 99L);

        assertNotNull(value);
        assertEquals(8L, value);
    }

    @Test
    public void testMissingLongValueButDefaultValue() {
        long value = properties.getLong("missingKey", 99L);

        assertNotNull(value);
        assertEquals(99L, value);
    }

    @Test
    public void testFloatValue() {
        float value = properties.getFloat("floatKey");

        assertNotNull(value);
        assertEquals(0.7F, value, 0.01);
    }

    @Test
    public void testFloatValueAndDefaultValue() {
        float value = properties.getFloat("floatKey", 9.99F);

        assertNotNull(value);
        assertEquals(0.7F, value, 0.01);
    }

    @Test
    public void testMissingFloatValueButDefaultValue() {
        float value = properties.getFloat("missingKey", 9.99F);

        assertNotNull(value);
        assertEquals(9.99F, value, 0.001);
    }

    @Test
    public void testDoubleValue() {
        double value = properties.getDouble("doubleKey");

        assertNotNull(value);
        assertEquals(0.8D, value, 0.01);
    }

    @Test
    public void testDoubleValueAndDefaultValue() {
        double value = properties.getDouble("doubleKey", 9.99D);

        assertNotNull(value);
        assertEquals(0.8D, value, 0.01);
    }

    @Test
    public void testMissingDoubleValueButDefaultValue() {
        double value = properties.getDouble("missingKey", 9.99D);

        assertNotNull(value);
        assertEquals(9.99D, value, 0.001);
    }

    @Test
    public void testBooleanValue() {
        boolean value = properties.getBoolean("booleanKey");

        assertNotNull(value);
        assertTrue(value);
    }

    @Test
    public void testBooleanValueAndDefaultValue() {
        boolean value = properties.getBoolean("booleanKey", Boolean.FALSE);

        assertNotNull(value);
        assertTrue(value);
    }

    @Test
    public void testMissingBooleanValueButDefaultValue() {
        boolean value = properties.getBoolean("missingKey", Boolean.FALSE);

        assertNotNull(value);
        assertFalse(value);
    }

    @Test
    public void testVar1Value() {
        String value = properties.getString("varKey1");

        assertNotNull(value);
        assertEquals("hey there", value);
    }

    @Test
    public void testVar2Value() {
        String value = properties.getString("varKey2");

        assertNotNull(value);
        assertEquals("hey there mr.", value);
    }
}
