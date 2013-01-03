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

import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
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

	private CreateUserInput createInput;
	private ReadUserInput readInput;
	private UpdateUserInput updateInput;
	private DeleteUserInput deleteInput;
	private PurgeUsersInput purgeInput;

	@Before
	public void setUp() {
		initMocks(this);

		createInput = TestDataFactory.createCreateUserJohnInput();
		readInput = TestDataFactory.createReadUserJohnInput();
		updateInput = TestDataFactory.createUpdateUserJohnInput();
		deleteInput = TestDataFactory.createDeleteUserJohnInput();
		purgeInput = TestDataFactory.createPurgeUsersInput();
	}

	@Test
	public void testCreateUserJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.createUser(createInput);

		verify(connection, times(4)).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testCreateUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.createUser(createInput);
	}

	@Test
	public void testCreateUserJohnWithoutAddress() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		createInput.getUser().setUserAddress(null);
		userDao.createUser(createInput);

		verify(connection, times(3)).prepareStatement(anyString(), anyInt());
	}
	
	@Test
	public void testReadUserJohn() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadUserJohnResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadUserOutput output = userDao.readUser(readInput);

		verify(connection, times(1)).prepareStatement(anyString(), anyInt());

		assertEquals(1, output.getUserList().size());
	}

	@Test
	public void testReadAllUsers() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadAllUsersResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadUserOutput output = userDao.readAllUsers();

		verify(connection, times(1)).prepareStatement(anyString(), anyInt());

		assertEquals(3, output.getUserList().size());
	}

	@Test(expected = DaoException.class)
	public void testReadUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.readUser(readInput);
	}

	@Test
	public void testUpdateUserJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.updateUser(updateInput);

		verify(connection, times(5)).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testUpdateUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.updateUser(updateInput);
	}

	@Test
	public void testUpdateUserJohnWithoutPassword() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		updateInput.setUserPassword(null);
		userDao.updateUser(updateInput);

		verify(connection, times(4)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserJohnWithoutAddress() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		updateInput.getUser().setUserAddress(null);
		userDao.updateUser(updateInput);

		verify(connection, times(4)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserJohnWithoutUserId() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		updateInput.getUser().setUserId(null);
		userDao.updateUser(updateInput);

		verifyZeroInteractions(connection);
	}

	@Test
	public void testUpdateUserJohnWithoutInfo() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		updateInput.getUser().setUserInfo(null);
		userDao.updateUser(updateInput);

		verify(connection, times(4)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserJohnWithoutPasswordAddressAndInfo() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		updateInput.setUserPassword(null);
		updateInput.getUser().setUserAddress(null);
		updateInput.getUser().setUserInfo(null);
		userDao.updateUser(updateInput);

		verify(connection, times(2)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteUserJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.deleteUser(deleteInput);

		verify(connection, times(1)).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testDeleteUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.deleteUser(deleteInput);
	}

	@Test
	public void testPurgeUsers() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.purgeUsers(purgeInput);

		verify(connection, times(1)).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testPurgeUsersPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.purgeUsers(purgeInput);
	}
}
