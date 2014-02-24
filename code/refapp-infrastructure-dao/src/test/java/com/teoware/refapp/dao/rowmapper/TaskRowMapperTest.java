package com.teoware.refapp.dao.rowmapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.task.Task;

public class TaskRowMapperTest {

    @InjectMocks
    private TaskRowMapper rowMapper;

    @Mock
    private ResultSet result;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testMapRow() throws SQLException, ParseException {
        when(result.getString(anyString())).thenReturn("a", "b", "c", "NEW");
        when(result.getTimestamp(anyString())).thenReturn(new Timestamp(0), new Timestamp(1));

        Task task = rowMapper.mapRow(result, 0);

        assertEquals("a", task.getUuid().getUuid());
        assertEquals("b", task.getTaskDetails().getTitle());
        assertEquals("c", task.getTaskDetails().getDescription());
        assertEquals(Status.NEW, task.getTaskStatus().getStatus());
        assertEquals(new Timestamp(0).getTime(), task.getTaskStatus().getCreated().getMillis());
        assertEquals(new Timestamp(1).getTime(), task.getTaskStatus().getModified().getMillis());

        verify(result, times(4)).getString(anyString());
        verify(result, times(2)).getTimestamp(anyString());
    }

    @Test(expected = SQLException.class)
    public void testMapRowThrowsSqlException() throws SQLException, ParseException {
        when(result.getString(anyString())).thenThrow(new SQLException());

        rowMapper.mapRow(result, 0);

        verify(result).getString(anyString());
    }
}
