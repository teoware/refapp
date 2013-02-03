package com.teoware.refapp.dao.rowmapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.model.common.Id;

public class UserIdRowMapperTest {

	@InjectMocks
	private UserIdRowMapper rowMapper;

	@Mock
	private ResultSet result;

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void testMapRow() throws SQLException, ParseException {
		when(result.getLong(anyString())).thenReturn(1L);

		Id id = rowMapper.mapRow(result, 0);

		assertNotNull(id);
		assertEquals(new Long(1L), id.getId());

		verify(result).getLong(anyString());
	}

	@Test(expected = SQLException.class)
	public void testMapRowThrowsSqlException() throws SQLException, ParseException {
		when(result.getLong(anyString())).thenThrow(new SQLException());

		rowMapper.mapRow(result, 0);

		verify(result).getLong(anyString());
	}
}
