package com.teoware.refapp.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.teoware.refapp.dao.dto.CreateUserAddressInput;
import com.teoware.refapp.dao.dto.CreateUserInfoInput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserAddressInput;
import com.teoware.refapp.dao.dto.UpdateUserInfoInput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserStatusInput;
import com.teoware.refapp.dao.test.TestResultSetFactory;
import com.teoware.refapp.test.util.TestDataFactory;

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
	private CreateUserInfoInput createInfoInput;
	private CreateUserAddressInput createAddressInput;
	private ReadUserInput readInput;
	private UpdateUserInput updateInput;
	private UpdateUserStatusInput updateStatusInput;
	private UpdateUserInfoInput updateInfoInput;
	private UpdateUserAddressInput updateAddressInput;
	private DeleteUserInput deleteInput;
	private PurgeUsersInput purgeInput;

	@Before
	public void setUp() {
		initMocks(this);

		createInput = TestDataFactory.createCreateUserInputJohn();
		createInfoInput = TestDataFactory.createCreateUserInfoInputJohn();
		createAddressInput = TestDataFactory.createCreateUserAddressInputJohn();
		readInput = TestDataFactory.createReadUserInputJohn();
		updateInput = TestDataFactory.createUpdateUserInputJohn();
		updateInfoInput = TestDataFactory.createUpdateUserInfoInputJohn();
		updateStatusInput = TestDataFactory.createUpdateUserStatusInputJohn();
		updateAddressInput = TestDataFactory.createUpdateUserAddressInputJohn();
		deleteInput = TestDataFactory.createDeleteUserInputJohn();
		purgeInput = TestDataFactory.createPurgeUsersInput();
	}

	@Test
	public void testCreateUserJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.createUser(createInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testCreateUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.createUser(createInput);
	}

	@Test
	public void testCreateUserInfoJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.createUserInfo(createInfoInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testCreateUserAddressJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.createUserAddress(createAddressInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testReadUserJohn() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadUserJohnResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadUserOutput output = userDao.readUser(readInput);

		verify(connection).prepareStatement(anyString(), anyInt());
		assertEquals(1, output.getUserList().size());
	}

	@Test
	public void testReadAllUsers() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadAllUsersResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadUserOutput output = userDao.readAllUsers();

		verify(connection).prepareStatement(anyString(), anyInt());
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
		verify(connection, times(1)).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testUpdateUserJohnThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.updateUser(updateInput);
	}

	@Test
	public void testUpdateUserInfoJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.updateUserInfo(updateInfoInput);

		verify(connection, times(1)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserStatusJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.updateUserStatus(updateStatusInput);

		verify(connection, times(1)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserAddressJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.updateUserAddress(updateAddressInput);

		verify(connection, times(1)).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteUserJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		userDao.deleteUser(deleteInput);

		verify(connection).prepareStatement(anyString(), anyInt());
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

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testPurgeUsersPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		userDao.purgeUsers(purgeInput);
	}
}
