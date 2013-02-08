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
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.dao.dto.CreateTaskDetailsInput;
import com.teoware.refapp.dao.dto.CreateTaskDetailsOutput;
import com.teoware.refapp.dao.dto.CreateTaskInput;
import com.teoware.refapp.dao.dto.CreateTaskOutput;
import com.teoware.refapp.dao.dto.DeleteTaskDetailsInput;
import com.teoware.refapp.dao.dto.DeleteTaskDetailsOutput;
import com.teoware.refapp.dao.dto.DeleteTaskInput;
import com.teoware.refapp.dao.dto.DeleteTaskOutput;
import com.teoware.refapp.dao.dto.DeleteTaskStatusInput;
import com.teoware.refapp.dao.dto.DeleteTaskStatusOutput;
import com.teoware.refapp.dao.dto.ReadTaskInput;
import com.teoware.refapp.dao.dto.ReadTaskOutput;
import com.teoware.refapp.dao.dto.UpdateTaskDetailsInput;
import com.teoware.refapp.dao.dto.UpdateTaskDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateTaskInput;
import com.teoware.refapp.dao.dto.UpdateTaskOutput;
import com.teoware.refapp.dao.dto.UpdateTaskStatusInput;
import com.teoware.refapp.dao.dto.UpdateTaskStatusOutput;
import com.teoware.refapp.dao.test.TestResultSetFactory;
import com.teoware.refapp.test.util.TestDataFactory;

public class TaskDaoBeanTest {

	@InjectMocks
	private TaskDaoBean dao;

	@Mock
	private DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private PreparedStatement statement;

	@Mock
	private ResultSet resultSet;

	private CreateTaskInput createInput;
	private CreateTaskDetailsInput createDetailsInput;
	private ReadTaskInput readInput;
	private UpdateTaskInput updateInput;
	private UpdateTaskDetailsInput updateDetailsInput;
	private UpdateTaskStatusInput updateStatusInput;
	private DeleteTaskInput deleteInput;
	private DeleteTaskDetailsInput deleteDetailsInput;
	private DeleteTaskStatusInput deleteStatusInput;

	@Before
	public void setUp() {
		initMocks(this);

		createInput = TestDataFactory.createCreateTaskInput1();
		createDetailsInput = TestDataFactory.createCreateTaskDetailsInput1();
		readInput = TestDataFactory.createReadTaskInput1();
		updateInput = TestDataFactory.createUpdateTaskInput1();
		updateDetailsInput = TestDataFactory.createUpdateTaskDetailsInput1();
		updateStatusInput = TestDataFactory.createUpdateTaskStatusInput1();
		deleteInput = TestDataFactory.createDeleteTaskInput();
		deleteDetailsInput = TestDataFactory.createDeleteTaskDetailsInput();
		deleteStatusInput = TestDataFactory.createDeleteTaskStatusInput();
	}

	@Test
	public void testCreateTask1() throws SQLException, DaoException {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		CreateTaskOutput output = dao.createTask(createInput);

		assertEquals(0, output.getRowsAffected());
		assertEquals(0, output.getId().getId().intValue());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testCreateTask1PrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		dao.createTask(createInput);
	}

	@Test
	public void testCreateTaskDetails1() throws Exception {
		when(resultSet.getLong(anyString())).thenReturn(0L);
		when(statement.getGeneratedKeys()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		CreateTaskDetailsOutput output = dao.createTaskDetails(createDetailsInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testReadTask1() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createReadTask1ResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		ReadTaskOutput output = dao.readTask(readInput);

		assertEquals(1, output.getTaskList().size());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test(expected = DaoException.class)
	public void testReadTask1PrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		dao.readTask(readInput);
	}

	@Test
	public void testUpdateTask1() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		UpdateTaskOutput output = dao.updateTask(updateInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateTaskNull() throws Exception {
		updateInput.setTitle(null);

		UpdateTaskOutput output = dao.updateTask(updateInput);

		assertEquals(0, output.getRowsAffected());
		verifyZeroInteractions(connection);
	}

	@Test(expected = DaoException.class)
	public void testUpdateTask1ThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

		dao.updateTask(updateInput);
	}

	@Test
	public void testUpdateTaskDetails1() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		UpdateTaskDetailsOutput output = dao.updateTaskDetails(updateDetailsInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateTaskDetailsNull() throws Exception {
		updateDetailsInput.setTaskDetails(null);

		UpdateTaskDetailsOutput output = dao.updateTaskDetails(updateDetailsInput);

		assertEquals(0, output.getRowsAffected());
		verifyZeroInteractions(connection);
	}

	@Test
	public void testUpdateTaskStatus1() throws Exception {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		UpdateTaskStatusOutput output = dao.updateTaskStatus(updateStatusInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testUpdateTaskStatusNull() throws Exception {
		updateStatusInput.setTaskStatus(null);

		UpdateTaskStatusOutput output = dao.updateTaskStatus(updateStatusInput);

		assertEquals(0, output.getRowsAffected());
		verifyZeroInteractions(connection);
	}

	@Test
	public void testDeleteTask1() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteTaskOutput output = dao.deleteTask(deleteInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteTaskDetails1() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteTaskDetailsOutput output = dao.deleteTaskDetails(deleteDetailsInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
	}

	@Test
	public void testDeleteTaskStatus1() throws Exception {
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

		DeleteTaskStatusOutput output = dao.deleteTaskStatus(deleteStatusInput);

		assertEquals(0, output.getRowsAffected());
		verify(connection).prepareStatement(anyString(), anyInt());
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
