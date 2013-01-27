package com.teoware.refapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
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

import com.teoware.refapp.dao.dto.CreateUserAddressInput;
import com.teoware.refapp.dao.dto.CreateUserDetailsInput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.DeleteUserAddressInput;
import com.teoware.refapp.dao.dto.DeleteUserDetailsInput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.DeleteUserPasswordInput;
import com.teoware.refapp.dao.dto.DeleteUserStatusInput;
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserAddressInput;
import com.teoware.refapp.dao.dto.UpdateUserDetailsInput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordInput;
import com.teoware.refapp.dao.dto.UpdateUserStatusInput;
import com.teoware.refapp.dao.test.TestResultSetFactory;
import com.teoware.refapp.test.util.TestDataFactory;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class UserDaoBeanTest {

	@InjectMocks
	private UserDaoBean dao;

	@Mock
	private DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private PreparedStatement statement;

	@Mock
	private ResultSet resultSet;

	private CreateUserInput createInput;
	private CreateUserDetailsInput createDetailsInput;
	private CreateUserAddressInput createAddressInput;
	private ReadUserInput readInput;
	private UpdateUserInput updateInput;
	private UpdateUserStatusInput updateStatusInput;
	private UpdateUserDetailsInput updateDetailsInput;
	private UpdateUserAddressInput updateAddressInput;
	private UpdateUserPasswordInput updatePasswordInput;
	private DeleteUserInput deleteInput;
	private DeleteUserDetailsInput deleteDetailsInput;
	private DeleteUserStatusInput deleteStatusInput;
	private DeleteUserAddressInput deleteAddressInput;
	private DeleteUserPasswordInput deletePasswordInput;
	private PurgeUsersInput purgeInput;

	@Before
	public void setUp() {
		initMocks(this);

		createInput = TestDataFactory.createCreateUserInputJohn();
		createDetailsInput = TestDataFactory.createCreateUserDetailsInputJohn();
		createAddressInput = TestDataFactory.createCreateUserAddressInputJohn();
		readInput = TestDataFactory.createReadUserInputJohn();
		updateInput = TestDataFactory.createUpdateUserInputJohn();
		updateDetailsInput = TestDataFactory.createUpdateUserDetailsInputJohn();
		updateStatusInput = TestDataFactory.createUpdateUserStatusInputJohn();
		updateAddressInput = TestDataFactory.createUpdateUserAddressInputJohn();
		updatePasswordInput = TestDataFactory.createUpdateUserPasswordInputJohn();
		deleteInput = TestDataFactory.createDeleteUserInput();
		deleteDetailsInput = TestDataFactory.createDeleteUserDetailsInput();
		deleteStatusInput = TestDataFactory.createDeleteUserStatusInput();
		deleteAddressInput = TestDataFactory.createDeleteUserAddressInput();
		deletePasswordInput = TestDataFactory.createDeleteUserPasswordInput();
		purgeInput = TestDataFactory.createPurgeUsersInput();
	}

	@Test
	public void testCreateUserJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.createUser(createInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testCreateUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		dao.createUser(createInput);
	}

	@Test
	public void testCreateUserInfoJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.createUserDetails(createDetailsInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testCreateUserAddressJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.createUserAddress(createAddressInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testCreateUserAddressNull() throws Exception {
		createAddressInput.setUserAddress(null);

		dao.createUserAddress(createAddressInput);

		verifyZeroInteractions(connection);
	}

	@Test
	public void testReadUserJohn() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadUserJohnResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadUserOutput output = dao.readUser(readInput);

		verify(connection).prepareStatement(anyString(), anyInt());
		assertEquals(1, output.getUserList().size());
	}

	@Test
	public void testReadAllUsers() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadAllUsersResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadUserOutput output = dao.readAllUsers();

		verify(connection).prepareStatement(anyString(), anyInt());
		assertEquals(3, output.getUserList().size());
	}

	@Test(expected = DaoException.class)
	public void testReadUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		dao.readUser(readInput);
	}

	@Test
	public void testUpdateUserJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.updateUser(updateInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserNull() throws Exception {
		updateInput.setUser(null);

		dao.updateUser(updateInput);

		verifyZeroInteractions(connection);
	}

	@Test(expected = DaoException.class)
	public void testUpdateUserJohnThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		dao.updateUser(updateInput);
	}

	@Test
	public void testUpdateUserDetailsJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.updateUserDetails(updateDetailsInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserDetailsNull() throws Exception {
		updateDetailsInput.setUserDetails(null);

		dao.updateUserDetails(updateDetailsInput);

		verifyZeroInteractions(connection);
	}

	@Test
	public void testUpdateUserStatusJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.updateUserStatus(updateStatusInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserStatusNull() throws Exception {
		updateStatusInput.setUserStatus(null);

		dao.updateUserStatus(updateStatusInput);

		verifyZeroInteractions(connection);
	}

	@Test
	public void testUpdateUserAddressJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.updateUserAddress(updateAddressInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserAddressNull() throws Exception {
		updateAddressInput.setUserAddress(null);

		dao.updateUserAddress(updateAddressInput);

		verifyZeroInteractions(connection);
	}

	@Test
	public void testUpdateUserPasswordJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.updateUserPassword(updatePasswordInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserPasswordNull() throws Exception {
		updatePasswordInput.setUserPassword(null);

		dao.updateUserPassword(updatePasswordInput);

		verifyZeroInteractions(connection);
	}

	@Test
	public void testDeleteUserJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.deleteUser(deleteInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteUserDetailsJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.deleteUserDetails(deleteDetailsInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteUserStatusJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.deleteUserStatus(deleteStatusInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteUserAddressJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.deleteUserAddress(deleteAddressInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteUserPasswordJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.deleteUserPassword(deletePasswordInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testDeleteUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		dao.deleteUser(deleteInput);
	}

	@Test
	public void testPurgeUsers() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		dao.purgeUsers(purgeInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testPurgeUsersGreedy() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		purgeInput.setGreedy(Boolean.TRUE);

		dao.purgeUsers(purgeInput);

		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testPurgeUsersPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		dao.purgeUsers(purgeInput);
	}

	@Test
	public void testPersistConnection() {
		dao.persistConnection();

		assertTrue(dao.getPersistConnection());
	}

	@Test
	public void testTerminateConnection() throws SQLException {
		dao.terminateConnection();

		assertFalse(dao.getPersistConnection());
		verify(connection).close();
	}
}
