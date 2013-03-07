package com.teoware.refapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.beans.IntrospectionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.dao.rowmapper.RowMapper;
import com.teoware.refapp.dao.util.ChangeResult;
import com.teoware.refapp.dao.util.SQL;

public class DaoTest {

	@InjectMocks
	private Dao dao = new Dao() {
	};

	@Mock
	private DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private PreparedStatement statement;

	@Mock
	private ResultSet resultSet;

	@Mock
	private RowMapper<?> rowMapper;

	private SQL sql;

	@Before
	public void setUp() throws Exception {
		initMocks(this);

		sql = new SQL("whatever");
	}

	@Test
	public void testBean() throws IntrospectionException {
		dao.setPersistConnection(Boolean.FALSE);
		assertFalse(dao.isPersistConnection());
		dao.setPersistConnection(Boolean.TRUE);
		assertTrue(dao.isPersistConnection());
	}

	@Test
	public void testInitializeWithNull() throws SQLException {
		dao.setConnection(null);
		when(dataSource.getConnection()).thenReturn(mock(Connection.class));
		dao.initialize(dataSource);

		assertNotNull(dao.createOrReuseConnection());
		verify(dataSource).getConnection();
		verifyNoMoreInteractions(dataSource);
	}

	@Test
	public void testInitializeWithClosed() throws SQLException {
		when(connection.isClosed()).thenReturn(Boolean.TRUE);
		Connection con1 = mock(Connection.class);
		when(con1.isClosed()).thenReturn(Boolean.FALSE);
		when(dataSource.getConnection()).thenReturn(con1);
		dao.initialize(dataSource);

		Connection con2 = dao.createOrReuseConnection();
		assertNotNull(con2);
		assertFalse(con2.isClosed());
		verify(dataSource).getConnection();
		verifyNoMoreInteractions(dataSource);
	}

	@Test
	public void testCloseConnection() throws SQLException {
		dao.setPersistConnection(Boolean.FALSE);
		dao.closeConnection();

		verify(connection).close();
		verifyNoMoreInteractions(connection);
	}

	@Test
	public void testCloseConnectionWithPersistTrue() throws SQLException {
		dao.setPersistConnection(Boolean.TRUE);
		dao.closeConnection();

		verifyZeroInteractions(connection);
	}

	@Test
	public void testCloseConnectionWithNull() throws SQLException {
		dao.setPersistConnection(Boolean.FALSE);
		dao.setConnection(null);
		dao.closeConnection();

		verifyZeroInteractions(connection);
	}

	@Test
	public void testCloseConnectionThrowsSQLException() throws SQLException {
		doThrow(new SQLException()).when(connection).close();
		dao.setPersistConnection(Boolean.FALSE);
		dao.closeConnection();

		verify(connection).close();
	}

	@Test
	public void testCreate() throws DaoException, SQLException {
		when(statement.executeUpdate()).thenReturn(1);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		ChangeResult result = dao.create(sql, "param");

		assertEquals(1, result.getRowsAffected());
	}

	@Test(expected = DaoException.class)
	public void testCreateThrowsSQLException() throws DaoException, SQLException {
		when(statement.executeUpdate()).thenThrow(new SQLException());
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		dao.create(sql, "param");
	}

	@Test
	public void testRead() throws DaoException, SQLException {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		List<String> result = dao.read(sql, new StringRowMapper());

		assertEquals(1, result.size());
		assertEquals("row 1", result.get(0));
	}

	@Test(expected = DaoException.class)
	public void testReadThrowsSQLException() throws DaoException, SQLException {
		when(statement.executeQuery()).thenThrow(new SQLException());
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		dao.read(sql, new StringRowMapper());
	}

	@Test(expected = DaoException.class)
	public void testReadThrowsParseException() throws DaoException, SQLException, ParseException {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		when(rowMapper.mapRow(any(ResultSet.class), anyInt())).thenThrow(new ParseException("whatever", 0));
		dao.read(sql, rowMapper);
	}

	@Test
	public void testReadWithParams() throws DaoException, SQLException {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		List<String> result = dao.read(sql, new StringRowMapper(), "whatever");

		assertEquals(1, result.size());
		assertEquals("row 1", result.get(0));
	}

	@Test(expected = DaoException.class)
	public void testReadWithParamsThrowsSQLException() throws DaoException, SQLException {
		when(statement.executeQuery()).thenThrow(new SQLException());
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		dao.read(sql, new StringRowMapper(), "whatever");
	}

	@Test(expected = DaoException.class)
	public void testReadWithParamsThrowsParseException() throws DaoException, SQLException, ParseException {
		when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		when(rowMapper.mapRow(any(ResultSet.class), anyInt())).thenThrow(new ParseException("whatever", 0));
		dao.read(sql, rowMapper, "whatever");
	}

	@Test
	public void testUpdate() throws DaoException, SQLException {
		when(statement.executeUpdate()).thenReturn(1);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		ChangeResult result = dao.update(sql, "param");

		assertEquals(1, result.getRowsAffected());
	}

	@Test(expected = DaoException.class)
	public void testUpdateThrowSQLException() throws DaoException, SQLException {
		when(statement.executeUpdate()).thenThrow(new SQLException());
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		dao.update(sql, "param");
	}

	@Test
	public void testDelete() throws DaoException, SQLException {
		when(statement.executeUpdate()).thenReturn(1);
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		ChangeResult result = dao.delete(sql, "param");

		assertEquals(1, result.getRowsAffected());
	}

	@Test(expected = DaoException.class)
	public void testDeleteThrowSQLException() throws DaoException, SQLException {
		when(statement.executeUpdate()).thenThrow(new SQLException());
		when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
		dao.delete(sql, "param");
	}

	@Test
	public void testRowCount() throws DaoException, SQLException {
		when(resultSet.getRow()).thenReturn(1);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(statement);
		int rows = dao.rowCount("whatever");

		assertEquals(1, rows);
	}

	@Test
	public void testRowCount100Rows() throws DaoException, SQLException {
		when(resultSet.getRow()).thenReturn(100);
		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(statement);
		int rows = dao.rowCount("whatever");

		assertEquals(100, rows);
	}

	@Test(expected = DaoException.class)
	public void testRowCountThrowSQLException() throws DaoException, SQLException {
		when(statement.executeQuery()).thenThrow(new SQLException());
		when(connection.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(statement);
		dao.rowCount("whatever");
	}

	public class StringRowMapper implements RowMapper<String> {

		private int i = 1;

		@Override
		public String mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
			return "row " + i++;
		}
	}
}
