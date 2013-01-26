package com.teoware.refapp.dao.util;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

public class ConnectionHandlerTest {

	@SuppressWarnings("static-access")
	@Test
	public void testCreateConnection() throws SQLException {
		DataSource dataSource = mock(DataSource.class);
		Connection connection = mock(Connection.class);

		when(dataSource.getConnection()).thenReturn(connection);

		new ConnectionHandler().createConnection(dataSource);

		verify(dataSource, times(1)).getConnection();
	}

	@Test(expected = SQLException.class)
	public void testCreateConnectionThrowsSQLException() throws SQLException {
		DataSource dataSource = mock(DataSource.class);

		doThrow(SQLException.class).when(dataSource).getConnection();

		ConnectionHandler.createConnection(dataSource);
	}

	@Test
	public void testCloseConnection() throws SQLException {
		Connection connection = mock(Connection.class);

		ConnectionHandler.closeConnection(connection, false);

		verify(connection, times(1)).close();
	}

	@Test
	public void testCloseConnectionWithPersist() throws SQLException {
		Connection connection = mock(Connection.class);

		ConnectionHandler.closeConnection(connection, true);

		verifyZeroInteractions(connection);
	}

	@Test(expected = SQLException.class)
	public void testCloseConnectionThrowsSQLException() throws SQLException {
		Connection connection = mock(Connection.class);

		doThrow(SQLException.class).when(connection).close();

		ConnectionHandler.closeConnection(connection, false);
	}
}
