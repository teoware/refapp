package com.teoware.refapp.schema.adapters;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.joda.time.DateTime;
import org.junit.BeforeClass;
import org.junit.Test;

public class TimeAdapterTest {

    private static String parseTimeString;
    private static DateTime printTime;

    @BeforeClass
    public static void oneTimeSetUp() throws Exception {
        parseTimeString = "01:01:01.000";
        printTime = new DateTime().withYear(1999).withMonthOfYear(12).withDayOfMonth(12).withHourOfDay(12)
                .withMinuteOfHour(12).withSecondOfMinute(12).withMillisOfSecond(0);
    }

    @Test
    public void testParseStringSuccessfully() throws Exception {
        DateTime date = TimeAdapter.parse(parseTimeString);

        assertEquals(1, date.getHourOfDay());
        assertEquals(1, date.getMinuteOfHour());
        assertEquals(1, date.getSecondOfMinute());
        assertEquals(0, date.getMillisOfSecond());
    }

    @Test
    public void testParseDateTimeStringThrowsIllegalArgumentException() throws Exception {
        try {
            TimeAdapter.parse("2012-01-01T01:01:01.000");

            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testParseDateStringThrowsIllegalArgumentException() throws Exception {
        try {
            TimeAdapter.parse("2012-01-01");

            fail("Should throw exception");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void testPrintDateTimeSuccessfully() throws Exception {
        String dateString = TimeAdapter.print(printTime);

        assertEquals("12:12:12.000", dateString);
    }
}
