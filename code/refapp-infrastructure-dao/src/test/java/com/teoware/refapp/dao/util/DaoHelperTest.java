package com.teoware.refapp.dao.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

import java.lang.reflect.Constructor;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.teoware.refapp.model.enums.Status;

public class DaoHelperTest {

    @Mock
    private PreparedStatement statement;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        Constructor<DaoHelper> constructor = DaoHelper.class.getDeclaredConstructor();
        assertFalse(constructor.isAccessible());
        constructor.setAccessible(true);
        assertNotNull(constructor.newInstance());
    }

    @Test
    public void testProcessParameterWithNull() throws SQLException {
        DaoHelper.processParameter(statement, null, 1);

        verify(statement).setObject(anyInt(), anyObject());
        verifyNoMoreInteractions(statement);
    }

    @Test
    public void testProcessParameterWithEnum() throws SQLException {
        DaoHelper.processParameter(statement, Status.ACTIVE, 1);

        verify(statement).setString(anyInt(), anyString());
        verifyNoMoreInteractions(statement);
    }

    @Test
    public void testProcessParameterWithString() throws SQLException {
        DaoHelper.processParameter(statement, "abcd", 1);

        verify(statement).setString(anyInt(), anyString());
        verifyNoMoreInteractions(statement);
    }

    @Test
    public void testProcessParameterWithCharSeq() throws SQLException {
        CharSequence cs = "abcd";
        DaoHelper.processParameter(statement, cs, 1);

        verify(statement).setString(anyInt(), anyString());
        verifyNoMoreInteractions(statement);
    }

    @Test
    public void testProcessParameterWithInt() throws SQLException {
        DaoHelper.processParameter(statement, 1, 1);

        verify(statement).setInt(anyInt(), anyInt());
        verifyNoMoreInteractions(statement);
    }

    @Test
    public void testProcessParameterWithLong() throws SQLException {
        DaoHelper.processParameter(statement, 1L, 1);

        verify(statement).setLong(anyInt(), anyLong());
        verifyNoMoreInteractions(statement);
    }

    @Test
    public void testProcessParameterWithFloat() throws SQLException {
        DaoHelper.processParameter(statement, 1F, 1);

        verify(statement).setBigDecimal(anyInt(), any(BigDecimal.class));
        verifyNoMoreInteractions(statement);
    }

    @Test
    public void testProcessParameterWithDouble() throws SQLException {
        DaoHelper.processParameter(statement, 1D, 1);

        verify(statement).setBigDecimal(anyInt(), any(BigDecimal.class));
        verifyNoMoreInteractions(statement);
    }

    @Test
    public void testProcessParameterWithDate() throws SQLException {
        DaoHelper.processParameter(statement, new java.util.Date(), 1);

        verify(statement).setDate(anyInt(), any(java.sql.Date.class));
        verifyNoMoreInteractions(statement);
    }

    @Test
    public void testProcessParameterWithCalendar() throws SQLException {
        DaoHelper.processParameter(statement, Calendar.getInstance(), 1);

        verify(statement).setTimestamp(anyInt(), any(java.sql.Timestamp.class));
        verifyNoMoreInteractions(statement);
    }

    @Test
    public void testProcessParameterWithObject() throws SQLException {
        DaoHelper.processParameter(statement, new Object(), 1);

        verify(statement).setObject(anyInt(), anyObject());
        verifyNoMoreInteractions(statement);
    }

    @Test
    public void testProcessParameterWithDummyClass() throws SQLException {
        DaoHelper.processParameter(statement, new DummyClass(), 1);

        verify(statement).setObject(anyInt(), anyObject());
        verifyNoMoreInteractions(statement);
    }

    public class DummyClass {
    }
}
