package com.teoware.refapp.dao.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.teoware.refapp.dao.rowmapper.RowMapper;

public class RowMapperResultSetExtractorTest {

    @Mock
    private ResultSet resultSet;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testExtractData() throws SQLException, ParseException {
        when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);

        ResultSetExtractor<List<String>> resultSetExtractor = new RowMapperResultSetExtractor<String>(
                new StringRowMapper());
        List<String> rows = resultSetExtractor.extractData(resultSet);

        assertNotNull(rows);
        assertTrue(rows.size() == 2);
        assertEquals("row 1", rows.get(0));
        assertEquals("row 2", rows.get(1));
    }

    @Test
    public void testExtractDataWithRowsExpected() throws SQLException, ParseException {
        when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);

        ResultSetExtractor<List<String>> resultSetExtractor = new RowMapperResultSetExtractor<String>(
                new StringRowMapper(), 2);
        List<String> rows = resultSetExtractor.extractData(resultSet);

        assertNotNull(rows);
        assertTrue(rows.size() == 2);
        assertEquals("row 1", rows.get(0));
        assertEquals("row 2", rows.get(1));
    }

    @Test(expected = SQLException.class)
    public void testExtractDataThrowsSQLException() throws SQLException, ParseException {
        when(resultSet.next()).thenThrow(new SQLException());

        ResultSetExtractor<List<String>> resultSetExtractor = new RowMapperResultSetExtractor<String>(
                new StringRowMapper());
        resultSetExtractor.extractData(resultSet);
    }

    @Test(expected = ParseException.class)
    public void testExtractDataThrowsParseException() throws SQLException, ParseException {
        when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        StringRowMapper rowMapper = mock(StringRowMapper.class);
        when(rowMapper.mapRow(any(ResultSet.class), anyInt())).thenThrow(new ParseException("whatever", 0));

        ResultSetExtractor<List<String>> resultSetExtractor = new RowMapperResultSetExtractor<String>(rowMapper);
        resultSetExtractor.extractData(resultSet);
    }

    public class StringRowMapper implements RowMapper<String> {

        private int i = 1;

        @Override
        public String mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
            return "row " + i++;
        }
    }
}
