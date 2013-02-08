package com.teoware.refapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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

	private CreateUserInput createInput;
	private CreateUserDetailsInput createDetailsInput;
	private CreateUserAddressInput createAddressInput;
	private CreateUserPasswordInput createPasswordInput;
	private ReadUserInput readInput;
	private ReadUserPasswordInput readPasswordInput;
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
		createPasswordInput = TestDataFactory.createCreateUserPasswordInputJohn();
		readInput = TestDataFactory.createReadUserInputJohn();
		readPasswordInput = TestDataFactory.createReadUserPasswordInputJohn();
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

		CreateUserOutput output = dao.createUser(createInput);

		assertEquals(0, output.getRowsAffected());
		assertEquals(0, output.getId().getId().intValue());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testCreateUserJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		dao.createUser(createInput);
	}

	@Test
	public void testCreateUserDetailsJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		CreateUserDetailsOutput output = dao.createUserDetails(createDetailsInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testCreateUserAddressJohn() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		CreateUserAddressOutput output = dao.createUserAddress(createAddressInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testCreateUserAddressNull() throws Exception {
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

		CreateUserPasswordOutput output = dao.createUserPassword(createPasswordInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
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
	public void testReadUserIdJohn() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadUserIdJohnResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		Id id = dao.readUserId(readInput.getUsername());

		verify(connection).prepareStatement(anyString(), anyInt());
		assertNotNull(id);
	}

	@Test(expected = DaoException.class)
	public void testReadUserIdJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		dao.readUserId(readInput.getUsername());
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
	public void testReadUserPasswordJohn() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadUserPasswordJohnResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadUserPasswordOutput output = dao.readUserPassword(readPasswordInput);

		verify(connection).prepareStatement(anyString(), anyInt());
		assertEquals(1, output.getUserPasswordList().size());
	}

	@Test
	public void testUpdateUserJohn() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		UpdateUserOutput output = dao.updateUser(updateInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserNull() throws Exception {
		updateInput.setUser(null);

		UpdateUserOutput output = dao.updateUser(updateInput);

		assertEquals(0, output.getRowsAffected());
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

		UpdateUserDetailsOutput output = dao.updateUserDetails(updateDetailsInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserDetailsNull() throws Exception {
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

		UpdateUserStatusOutput output = dao.updateUserStatus(updateStatusInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserStatusNull() throws Exception {
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

		UpdateUserAddressOutput output = dao.updateUserAddress(updateAddressInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserAddressNull() throws Exception {
		updateAddressInput.setUserAddress(null);

		UpdateUserAddressOutput output = dao.updateUserAddress(updateAddressInput);

		assertEquals(0, output.getRowsAffected());
		verifyZeroInteractions(connection);
	}

	@Test
	public void testUpdateUserPasswordJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		UpdateUserPasswordOutput output = dao.updateUserPassword(updatePasswordInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateUserPasswordNull() throws Exception {
		updatePasswordInput.setUserPassword(null);

		UpdateUserPasswordOutput output = dao.updateUserPassword(updatePasswordInput);

		assertEquals(0, output.getRowsAffected());
		verifyZeroInteractions(connection);
	}

	@Test
	public void testDeleteUserJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteUserOutput output = dao.deleteUser(deleteInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteUserDetailsJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteUserDetailsOutput output = dao.deleteUserDetails(deleteDetailsInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteUserStatusJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteUserStatusOutput output = dao.deleteUserStatus(deleteStatusInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteUserAddressJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteUserAddressOutput output = dao.deleteUserAddress(deleteAddressInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteUserPasswordJohn() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteUserPasswordOutput output = dao.deleteUserPassword(deletePasswordInput);

		assertEquals(0, output.getRowsAffected());
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

		PurgeUsersOutput output = dao.purgeUsers(purgeInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testPurgeUsersGreedy() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		purgeInput.setGreedy(Boolean.TRUE);

		PurgeUsersOutput output = dao.purgeUsers(purgeInput);

		assertEquals(0, output.getRowsAffected());
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

		assertTrue(dao.isPersistConnection());
	}

	@Test
	public void testTerminateConnection() throws SQLException {
		dao.terminateConnection();

		assertFalse(dao.isPersistConnection());
		verify(connection).close();
	}
}
