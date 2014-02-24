package com.teoware.refapp.dao.util;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.dao.util.Converters.Converter;
import com.teoware.refapp.model.common.Username;
import com.teoware.refapp.model.common.Uuid;
import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map;
import org.joda.time.DateTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConvertersTest {

    private static Map<Class<?>, Converter<?>> converters;

    @Mock
    private PreparedStatement ps;

    @Before
    public void setUp() {
        initMocks(this);
        converters = Converters.getConverters();
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<Converters> constructor = Converters.class.getDeclaredConstructor();
        assertFalse(constructor.isAccessible());
        constructor.setAccessible(true);
        assertNotNull(constructor.newInstance());
    }

    @Test
    public void testObjectConverter() throws SQLException {
        Object param = new Object();
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof Object);
        assertTrue(param == obj);
        conv.setParam(ps, param, 0);
        verify(ps).setObject(anyInt(), anyObject());
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testStringConverter() throws SQLException {
        String param = "whatever";
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof String);
        assertEquals(param, obj.toString());
        conv.setParam(ps, param, 0);
        verify(ps).setString(anyInt(), anyString());
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testIntConverter() throws SQLException {
        Integer param = 1234;
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof Integer);
        assertEquals(param, ((Integer) obj));
        conv.setParam(ps, param, 0);
        verify(ps).setInt(anyInt(), anyInt());
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testLongConverter() throws SQLException {
        Long param = 1234L;
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof Long);
        assertEquals(param, ((Long) obj));
        conv.setParam(ps, param, 0);
        verify(ps).setLong(anyInt(), anyLong());
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testBigDecimalConverterUsingFloat() throws SQLException {
        Float param = 0.123F;
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof BigDecimal);
        assertEquals(param, ((BigDecimal) obj).floatValue(), 0.0001);
        conv.setParam(ps, param, 0);
        verify(ps).setBigDecimal(anyInt(), any(BigDecimal.class));
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testBigDecimalConverterUsingDouble() throws SQLException {
        Double param = 0.123D;
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof BigDecimal);
        assertEquals(param, ((BigDecimal) obj).doubleValue(), 0.0001);
        conv.setParam(ps, param, 0);
        verify(ps).setBigDecimal(anyInt(), any(BigDecimal.class));
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testBigDecimalConverterUsingBigDecimal() throws SQLException {
        BigDecimal param = new BigDecimal(0.123D);
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof BigDecimal);
        assertEquals(param.doubleValue(), ((BigDecimal) obj).doubleValue(), 0.0001);
        conv.setParam(ps, param, 0);
        verify(ps).setBigDecimal(anyInt(), any(BigDecimal.class));
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testDateConverterUsingUtilDate() throws SQLException {
        java.util.Date param = new java.util.Date(0);
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof java.sql.Date);
        assertEquals(param.getTime(), ((java.sql.Date) obj).getTime());
        conv.setParam(ps, param, 0);
        verify(ps).setDate(anyInt(), any(java.sql.Date.class));
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testDateConverterUsingSqlDate() throws SQLException {
        java.sql.Date param = new java.sql.Date(0);
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof java.sql.Date);
        assertEquals(param.getTime(), ((java.sql.Date) obj).getTime());
        conv.setParam(ps, param, 0);
        verify(ps).setDate(anyInt(), any(java.sql.Date.class));
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testTimestampConverterUsingDateTime() throws SQLException {
        DateTime param = DateTime.now();
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof java.sql.Timestamp);
        assertEquals(param.getMillis(), ((java.sql.Timestamp) obj).getTime());
        conv.setParam(ps, param, 0);
        verify(ps).setTimestamp(anyInt(), any(java.sql.Timestamp.class));
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testTimestampConverterUsingCalendar() throws SQLException {
        Calendar param = Calendar.getInstance();
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof java.sql.Timestamp);
        assertEquals(param.getTimeInMillis(), ((java.sql.Timestamp) obj).getTime());
        conv.setParam(ps, param, 0);
        verify(ps).setTimestamp(anyInt(), any(java.sql.Timestamp.class));
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testTimestampConverterUsingTimestamp() throws SQLException {
        java.sql.Timestamp param = new java.sql.Timestamp(0);
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof java.sql.Timestamp);
        assertEquals(param.getTime(), ((java.sql.Timestamp) obj).getTime());
        conv.setParam(ps, param, 0);
        verify(ps).setTimestamp(anyInt(), any(java.sql.Timestamp.class));
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testIdConverter() throws SQLException {
        Id param = new Id(123L);
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof Long);
        assertEquals(param.getId(), ((Long) obj));
        conv.setParam(ps, param, 0);
        verify(ps).setLong(anyInt(), anyLong());
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testUsernameConverter() throws SQLException {
        Username param = new Username("whatever");
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof String);
        assertEquals(param.getUsername(), obj.toString());
        conv.setParam(ps, param, 0);
        verify(ps).setString(anyInt(), anyString());
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testTitleConverter() throws SQLException {
        Uuid param = new Uuid("whatever");
        Converter<?> conv = converters.get(param.getClass());

        assertNotNull(conv);
        Object obj = conv.convert(param);
        assertNotNull(obj);
        assertTrue(obj instanceof String);
        assertEquals(param.getUuid(), obj.toString());
        conv.setParam(ps, param, 0);
        verify(ps).setString(anyInt(), anyString());
        verifyNoMoreInteractions(ps);
    }

    @Test
    public void testUnknownConverter() throws SQLException {
        DummyClass param = new DummyClass();
        Converter<?> conv = converters.get(param.getClass());

        assertNull(conv);
    }

    public class DummyClass {
    }
}
