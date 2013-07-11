package com.teoware.refapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import com.teoware.refapp.dao.dto.CreateUserAddressOutput;
import com.teoware.refapp.dao.dto.CreateUserDetailsInput;
import com.teoware.refapp.dao.dto.CreateUserDetailsOutput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.CreateUserPasswordInput;
import com.teoware.refapp.dao.dto.CreateUserPasswordOutput;
import com.teoware.refapp.dao.dto.DeleteUserAddressInput;
import com.teoware.refapp.dao.dto.DeleteUserAddressOutput;
import com.teoware.refapp.dao.dto.DeleteUserDetailsInput;
import com.teoware.refapp.dao.dto.DeleteUserDetailsOutput;
import com.teoware.refapp.dao.dto.DeleteUserInput;
import com.teoware.refapp.dao.dto.DeleteUserOutput;
import com.teoware.refapp.dao.dto.DeleteUserPasswordInput;
import com.teoware.refapp.dao.dto.DeleteUserPasswordOutput;
import com.teoware.refapp.dao.dto.DeleteUserStatusInput;
import com.teoware.refapp.dao.dto.DeleteUserStatusOutput;
import com.teoware.refapp.dao.dto.PurgeUsersInput;
import com.teoware.refapp.dao.dto.PurgeUsersOutput;
import com.teoware.refapp.dao.dto.ReadUserInput;
import com.teoware.refapp.dao.dto.ReadUserOutput;
import com.teoware.refapp.dao.dto.ReadUserPasswordInput;
import com.teoware.refapp.dao.dto.ReadUserPasswordOutput;
import com.teoware.refapp.dao.dto.ReadUsersInput;
import com.teoware.refapp.dao.dto.ReadUsersOutput;
import com.teoware.refapp.dao.dto.UpdateUserAddressInput;
import com.teoware.refapp.dao.dto.UpdateUserAddressOutput;
import com.teoware.refapp.dao.dto.UpdateUserDetailsInput;
import com.teoware.refapp.dao.dto.UpdateUserDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateUserInput;
import com.teoware.refapp.dao.dto.UpdateUserOutput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordInput;
import com.teoware.refapp.dao.dto.UpdateUserPasswordOutput;
import com.teoware.refapp.dao.dto.UpdateUserStatusInput;
import com.teoware.refapp.dao.dto.UpdateUserStatusOutput;
import com.teoware.refapp.dao.test.TestResultSetFactory;
import com.teoware.refapp.model.common.Id;
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

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void testInitialize() throws SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Method[] methods = UserDaoBean.class.getMethods();
		for (Method m : methods) {
			System.out.println("***" + m.getName());
		}
	}

	@Test
	public void testCreateUserJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		CreateUserInput createInput = TestDataFactory.createCreateUserInputJohn();
		CreateUserOutput output = dao.createUser(createInput);

		assertEquals(0, output.getRowsAffected());
		assertEquals(0, output.getId().getId().intValue());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test(expected = DaoException.class)
	public void testCreateUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		CreateUserInput createInput = TestDataFactory.createCreateUserInputJohn();
		dao.createUser(createInput);

		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testCreateUserDetailsJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		CreateUserDetailsInput createDetailsInput = TestDataFactory.createCreateUserDetailsInputJohn();
		CreateUserDetailsOutput output = dao.createUserDetails(createDetailsInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testCreateUserAddressJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		CreateUserAddressInput createAddressInput = TestDataFactory.createCreateUserAddressInputJohn();
		CreateUserAddressOutput output = dao.createUserAddress(createAddressInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testCreateUserAddressNull() throws Exception {
		CreateUserAddressInput createAddressInput = TestDataFactory.createCreateUserAddressInputJohn();
		createAddressInput.setUserAddress(null);
		CreateUserAddressOutput output = dao.createUserAddress(createAddressInput);

		assertEquals(0, output.getRowsAffected());
		verifyZeroInteractions(connection);
	}

	@Test
	public void testCreateUserPasswordJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		CreateUserPasswordInput createPasswordInput = TestDataFactory.createCreateUserPasswordInputJohn();
		CreateUserPasswordOutput output = dao.createUserPassword(createPasswordInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testReadUserJohn() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadUserJohnResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadUserInput readInput = TestDataFactory.createReadUserInputJohn();
		ReadUserOutput output = dao.readUser(readInput);

		assertEquals(1, output.getUserList().size());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testReadUserIdJohn() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadUserIdJohnResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadUserInput readInput = TestDataFactory.createReadUserInputJohn();
		Id id = dao.readUserId(readInput.getUsername());

		assertNotNull(id);
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test(expected = DaoException.class)
	public void testReadUserIdJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		ReadUserInput readInput = TestDataFactory.createReadUserInputJohn();
		dao.readUserId(readInput.getUsername());

		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testReadAllUsers() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadAllUsersResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadUsersInput readInput = TestDataFactory.createReadUsersInput();
		ReadUsersOutput output = dao.readUsers(readInput);

		assertEquals(3, output.getUserList().size());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test(expected = DaoException.class)
	public void testReadUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		ReadUserInput readInput = TestDataFactory.createReadUserInputJohn();
		dao.readUser(readInput);

		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testReadUserPasswordJohn() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadUserPasswordJohnResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadUserPasswordInput readPasswordInput = TestDataFactory.createReadUserPasswordInputJohn();
		ReadUserPasswordOutput output = dao.readUserPassword(readPasswordInput);

		assertEquals(1, output.getUserPasswordList().size());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testUpdateUserJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		UpdateUserInput updateInput = TestDataFactory.createUpdateUserInputJohn();
		UpdateUserOutput output = dao.updateUser(updateInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testUpdateUserNull() throws Exception {
		UpdateUserInput updateInput = TestDataFactory.createUpdateUserInputJohn();
		updateInput.setUser(null);
		UpdateUserOutput output = dao.updateUser(updateInput);

		assertEquals(0, output.getRowsAffected());
		verifyZeroInteractions(connection);
	}

	@Test(expected = DaoException.class)
	public void testUpdateUserJohnThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		UpdateUserInput updateInput = TestDataFactory.createUpdateUserInputJohn();
		dao.updateUser(updateInput);

		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testUpdateUserDetailsJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		UpdateUserDetailsInput updateDetailsInput = TestDataFactory.createUpdateUserDetailsInputJohn();
		UpdateUserDetailsOutput output = dao.updateUserDetails(updateDetailsInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testUpdateUserDetailsNull() throws Exception {
		UpdateUserDetailsInput updateDetailsInput = TestDataFactory.createUpdateUserDetailsInputJohn();
		updateDetailsInput.setUserDetails(null);
		UpdateUserDetailsOutput output = dao.updateUserDetails(updateDetailsInput);

		assertEquals(0, output.getRowsAffected());
		verifyZeroInteractions(connection);
	}

	@Test
	public void testUpdateUserStatusJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		UpdateUserStatusInput updateStatusInput = TestDataFactory.createUpdateUserStatusInputJohn();
		UpdateUserStatusOutput output = dao.updateUserStatus(updateStatusInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testUpdateUserStatusNull() throws Exception {
		UpdateUserStatusInput updateStatusInput = TestDataFactory.createUpdateUserStatusInputJohn();
		updateStatusInput.setUserStatus(null);
		UpdateUserStatusOutput output = dao.updateUserStatus(updateStatusInput);

		assertEquals(0, output.getRowsAffected());
		verifyZeroInteractions(connection);
	}

	@Test
	public void testUpdateUserAddressJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		UpdateUserAddressInput updateAddressInput = TestDataFactory.createUpdateUserAddressInputJohn();
		UpdateUserAddressOutput output = dao.updateUserAddress(updateAddressInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testUpdateUserAddressNull() throws Exception {
		UpdateUserAddressInput updateAddressInput = TestDataFactory.createUpdateUserAddressInputJohn();
		updateAddressInput.setUserAddress(null);
		UpdateUserAddressOutput output = dao.updateUserAddress(updateAddressInput);

		assertEquals(0, output.getRowsAffected());
		verifyZeroInteractions(connection);
	}

	@Test
	public void testUpdateUserPasswordJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		UpdateUserPasswordInput updatePasswordInput = TestDataFactory.createUpdateUserPasswordInputJohn();
		UpdateUserPasswordOutput output = dao.updateUserPassword(updatePasswordInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testUpdateUserPasswordNull() throws Exception {
		UpdateUserPasswordInput updatePasswordInput = TestDataFactory.createUpdateUserPasswordInputJohn();
		updatePasswordInput.setUserPassword(null);
		UpdateUserPasswordOutput output = dao.updateUserPassword(updatePasswordInput);

		assertEquals(0, output.getRowsAffected());
		verifyZeroInteractions(connection);
	}

	@Test
	public void testDeleteUserJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteUserInput deleteInput = TestDataFactory.createDeleteUserInput();
		DeleteUserOutput output = dao.deleteUser(deleteInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testDeleteUserDetailsJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteUserDetailsInput deleteDetailsInput = TestDataFactory.createDeleteUserDetailsInput();
		DeleteUserDetailsOutput output = dao.deleteUserDetails(deleteDetailsInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testDeleteUserStatusJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteUserStatusInput deleteStatusInput = TestDataFactory.createDeleteUserStatusInput();
		DeleteUserStatusOutput output = dao.deleteUserStatus(deleteStatusInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testDeleteUserAddressJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteUserAddressInput deleteAddressInput = TestDataFactory.createDeleteUserAddressInput();
		DeleteUserAddressOutput output = dao.deleteUserAddress(deleteAddressInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testDeleteUserPasswordJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteUserPasswordInput deletePasswordInput = TestDataFactory.createDeleteUserPasswordInput();
		DeleteUserPasswordOutput output = dao.deleteUserPassword(deletePasswordInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test(expected = DaoException.class)
	public void testDeleteUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		DeleteUserInput deleteInput = TestDataFactory.createDeleteUserInput();
		dao.deleteUser(deleteInput);

		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testPurgeUsers() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		PurgeUsersInput purgeInput = TestDataFactory.createPurgeUsersInput();
		PurgeUsersOutput output = dao.purgeUsers(purgeInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testPurgeUsersGreedy() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		PurgeUsersInput purgeInput = TestDataFactory.createPurgeUsersInput();
		purgeInput.setGreedy(Boolean.TRUE);
		PurgeUsersOutput output = dao.purgeUsers(purgeInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).isClosed();
		verify(connection).prepareStatement(anyString(), anyInt());
		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test(expected = DaoException.class)
	public void testPurgeUsersPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		PurgeUsersInput purgeInput = TestDataFactory.createPurgeUsersInput();
		dao.purgeUsers(purgeInput);

		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testPersistConnection() {
		dao.persistConnection();

		assertTrue(dao.isPersistConnection());
	}

	@Test
	public void testTerminateConnection() throws SQLException {
		dao.terminateConnection();

		assertFalse(dao.isPersistConnection());
		verify(connection).close();
	}
}
