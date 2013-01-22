package com.teoware.refapp.dao.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import com.teoware.refapp.dao.rowmapper.UserRowMapper;
import com.teoware.refapp.dao.test.TestResultSetFactory;
import com.teoware.refapp.dao.util.ResultSetExtractor;
import com.teoware.refapp.dao.util.RowMapperResultSetExtractor;
import com.teoware.refapp.model.user.User;

public class RowMapperResultSetExtractorTest {

	@Test
	public void testExtractDataFromUserResultSet() throws SQLException, ParseException {
		ResultSetExtractor<List<User>> resultSetExtractor = new RowMapperResultSetExtractor<User>(new UserRowMapper(),
				0);
		ResultSet resultSet = TestResultSetFactory.createReadAllUsersResultSet();
		List<User> userList = resultSetExtractor.extractData(resultSet);

		assertNotNull(userList);
		assertTrue(userList.size() == 3);

		User user = userList.get(0);

		assertNotNull(user);
		assertNotNull(user.getUsername());
		assertNotNull(user.getUserDetails());
		assertNotNull(user.getUserAddress());

		assertEquals("john.doe", user.getUsername().getUsername());
		assertEquals("John", user.getUserDetails().getFirstName());
		assertEquals("Storgata 1", user.getUserAddress().getAddress());
	}
}
