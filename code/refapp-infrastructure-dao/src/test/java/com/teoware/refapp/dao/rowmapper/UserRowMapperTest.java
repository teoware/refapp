package com.teoware.refapp.dao.rowmapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.user.User;

public class UserRowMapperTest {

	@InjectMocks
	private UserRowMapper rowMapper;

	@Mock
	private ResultSet result;

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void testMapRow() throws SQLException, ParseException {
		when(result.getString(anyString())).thenReturn("a", "ACTIVE", "c", "d", "MALE", "f", "g", "h", "i", "j", "k",
				"l");
		when(result.getTimestamp(anyString())).thenReturn(new Timestamp(0), new Timestamp(1));
		when(result.getDate(anyString())).thenReturn(new Date(0));

		User user = rowMapper.mapRow(result, 0);

		assertNotNull(user);
		assertEquals("a", user.getUsername().getUsername());
		assertEquals(Status.ACTIVE, user.getUserStatus().getStatus());
		assertEquals(new Timestamp(0).getTime(), user.getUserStatus().getCreated().getMillis());
		assertEquals(new Timestamp(1).getTime(), user.getUserStatus().getModified().getMillis());
		assertEquals("c", user.getUserInfo().getFirstName());
		assertEquals("d", user.getUserInfo().getLastName());
		assertEquals(Gender.MALE, user.getUserInfo().getGender());

		verify(result, times(12)).getString(anyString());
		verify(result, times(2)).getTimestamp(anyString());
		verify(result).getDate(anyString());
	}

	@Test(expected = SQLException.class)
	public void testMapRowThrowsSqlException() throws SQLException, ParseException {
		when(result.getString(anyString())).thenThrow(new SQLException());

		rowMapper.mapRow(result, 0);

		verify(result).getString(anyString());
	}
}
