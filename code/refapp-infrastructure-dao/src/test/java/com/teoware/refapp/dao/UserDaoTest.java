package com.teoware.refapp.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.dao.dto.DeleteUserRequest;
import com.teoware.refapp.dao.dto.InsertUserRequest;
import com.teoware.refapp.dao.dto.PurgeUsersRequest;
import com.teoware.refapp.dao.dto.SelectUserRequest;
import com.teoware.refapp.dao.dto.SelectUserResponse;
import com.teoware.refapp.dao.dto.UpdateUserRequest;
import com.teoware.refapp.dao.test.TestDataFactory;
import com.teoware.refapp.dao.test.TestResultSetFactory;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class UserDaoTest {

	@InjectMocks
	private UserDaoBean userDao;

	@Mock
	private DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private PreparedStatement statement;

	@Mock
	private ResultSet resultSet;

	private InsertUserRequest insertRequest;
	private UpdateUserRequest updateRequest;
	private SelectUserRequest selectRequest;
	private DeleteUserRequest deleteRequest;
	private PurgeUsersRequest purgeRequest;

	@Before
	public void setUp() {
		initMocks(this);

		insertRequest = TestDataFactory.createInsertUserJohnRequest();
		updateRequest = TestDataFactory.createUpdateUserJohnRequest();
		selectRequest = TestDataFactory.createSelectUserJohnRequest();
		deleteRequest = TestDataFactory.createDeleteUserJohnRequest();
		purgeRequest = TestDataFactory.createPurgeUsersRequest();
	}

	@Test
	public void testInsertUserJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.insertUser(insertRequest);

		verify(connection, times(4)).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testInsertUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.insertUser(insertRequest);
	}

	@Test
	public void testInsertUserJohnWithoutAddress() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		insertRequest.getUser().setUserAddress(null);
		userDao.insertUser(insertRequest);

		verify(connection, times(3)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.updateUser(updateRequest);

		verify(connection, times(5)).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testUpdateUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.updateUser(updateRequest);
	}

	@Test
	public void testUpdateUserJohnWithoutPassword() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		updateRequest.setUserPassword(null);
		userDao.updateUser(updateRequest);

		verify(connection, times(4)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserJohnWithoutAddress() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		updateRequest.getUser().setUserAddress(null);
		userDao.updateUser(updateRequest);

		verify(connection, times(4)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserJohnWithoutUserId() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		updateRequest.getUser().setUserId(null);
		userDao.updateUser(updateRequest);

		verifyZeroInteractions(connection);
	}

	@Test
	public void testUpdateUserJohnWithoutInfo() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		updateRequest.getUser().setUserInfo(null);
		userDao.updateUser(updateRequest);

		verify(connection, times(4)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserJohnWithoutPasswordAddressAndInfo() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		updateRequest.setUserPassword(null);
		updateRequest.getUser().setUserAddress(null);
		updateRequest.getUser().setUserInfo(null);
		userDao.updateUser(updateRequest);

		verify(connection, times(2)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testSelectUserJohn() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createSelectUserJohnResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		SelectUserResponse response = userDao.selectUser(selectRequest);

		verify(connection, times(1)).prepareStatement(anyString(), anyInt());

		assertEquals(1, response.getUserList().size());
	}

	@Test
	public void testSelectAllUsers() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createSelectAllUsersResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		SelectUserResponse response = userDao.selectAllUsers();

		verify(connection, times(1)).prepareStatement(anyString(), anyInt());

		assertEquals(3, response.getUserList().size());
	}

	@Test(expected = DaoException.class)
	public void testSelectUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.selectUser(selectRequest);
	}

	@Test
	public void testDeleteUserJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.deleteUser(deleteRequest);

		verify(connection, times(1)).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testDeleteUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.deleteUser(deleteRequest);
	}

	@Test
	public void testPurgeUsers() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.purgeUsers(purgeRequest);

		verify(connection, times(1)).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testPurgeUsersPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.purgeUsers(purgeRequest);
	}
}
