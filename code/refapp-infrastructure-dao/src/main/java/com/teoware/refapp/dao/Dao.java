package com.teoware.refapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.dao.rowmapper.IdRowMapper;
import com.teoware.refapp.dao.rowmapper.RowMapper;
import com.teoware.refapp.dao.util.ChangeResult;
import com.teoware.refapp.dao.util.ConnectionHandler;
import com.teoware.refapp.dao.util.DaoHelper;
import com.teoware.refapp.dao.util.ResultSetExtractor;
import com.teoware.refapp.dao.util.RowMapperResultSetExtractor;
import com.teoware.refapp.dao.util.SQL;
import com.teoware.refapp.model.common.Id;

/**
 * Base DAO that holds functionality for common database operations.
 * 
 */
public abstract class Dao {

	private static final Logger LOG = LoggerFactory.getLogger(Dao.class);

	private static final int FIRST_COLUMN = 1;
	private static final String CREATE_ERROR_MESSAGE = "Create operation failed.";
	private static final String READ_ERROR_MESSAGE = "Read operation failed.";
	private static final String UPDATE_ERROR_MESSAGE = "Update operation failed.";
	private static final String DELETE_ERROR_MESSAGE = "Delete operation failed.";

	private DataSource dataSource;

	protected Connection connection;
	protected boolean persistConnection;
	protected IdRowMapper idRowMapper = new IdRowMapper();

	protected void initialize(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected ChangeResult create(SQL sql, Object... parameters) throws DaoException {
		try {
			return executeUpdate(sql, parameters);
		} catch (DaoException e) {
			LOG.error(e(CREATE_ERROR_MESSAGE, sql));
			throw new DaoException(e(CREATE_ERROR_MESSAGE, sql), e.getCause());
		}
	}

	protected <T> List<T> read(SQL sql, RowMapper<T> rowMapper) throws DaoException {
		Object[] parameters = null;
		return read(sql, rowMapper, parameters);
	}

	protected <T> List<T> read(SQL sql, RowMapper<T> rowMapper, Object... parameters) throws DaoException {
		try {
			return executeQuery(sql, rowMapper, parameters);
		} catch (DaoException e) {
			LOG.error(e(READ_ERROR_MESSAGE, sql));
			throw new DaoException(e(READ_ERROR_MESSAGE, sql), e.getCause());
		}
	}

	protected ChangeResult update(SQL sql, Object... parameters) throws DaoException {
		try {
			return executeUpdate(sql, parameters);
		} catch (DaoException e) {
			LOG.error(e(UPDATE_ERROR_MESSAGE, sql));
			throw new DaoException(e(UPDATE_ERROR_MESSAGE, sql), e.getCause());
		}
	}

	protected ChangeResult delete(SQL sql, Object... parameters) throws DaoException {
		try {
			return executeUpdate(sql, parameters);
		} catch (DaoException e) {
			LOG.error(e(DELETE_ERROR_MESSAGE, sql));
			throw new DaoException(e(DELETE_ERROR_MESSAGE, sql), e.getCause());
		}
	}

	public int rowCount(String table) throws DaoException {
		PreparedStatement statement = null;
		String sql = "SELECT * FROM " + table;
		try {
			createOrReuseConnection();
			statement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet result = statement.executeQuery();
			result.last();
			int rowCount = result.getRow();
			result.close();
			return rowCount;
		} catch (SQLException e) {
			LOG.error(e(READ_ERROR_MESSAGE, sql));
			throw new DaoException(e(READ_ERROR_MESSAGE, sql), e.getCause());
		} finally {
			closeConnection(statement);
		}
	}

	private <T> List<T> executeQuery(SQL sql, RowMapper<T> rowMapper, Object... parameters) throws DaoException {
		LOG.debug("Executing SQL statement: " + sql.getSql());
		PreparedStatement statement = null;
		try {
			statement = generatePreparedStatement(sql, parameters);
			ResultSet result = statement.executeQuery();
			int rowsExpected = 0;
			ResultSetExtractor<List<T>> resultSetExtractor = new RowMapperResultSetExtractor<T>(rowMapper, rowsExpected);
			return resultSetExtractor.extractData(result);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} catch (ParseException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			closeConnection(statement);
		}
	}

	private ChangeResult executeUpdate(SQL sql, Object... parameters) throws DaoException {
		LOG.debug("Executing SQL statement: " + sql.getSql());
		PreparedStatement statement = null;
		try {
			statement = generatePreparedStatement(sql, parameters);
			int rowsAffected = statement.executeUpdate();
			Id generatedKey = getGeneratedKey(statement);
			return new ChangeResult(rowsAffected, generatedKey);
		} catch (SQLException e) {
			throw new DaoException(e.getMessage(), e);
		} finally {
			closeConnection(statement);
		}
	}
	
	private Id getGeneratedKey(PreparedStatement statement) {
		try {
			ResultSet rs = statement.getGeneratedKeys();
			rs.next();
			return new Id(rs.getLong(FIRST_COLUMN));
		} catch (Exception e) {
			return null;
		}
	}

	protected PreparedStatement generatePreparedStatement(SQL sql, Object... parameters) throws SQLException {
		createOrReuseConnection();
		PreparedStatement statement = connection.prepareStatement(sql.getSql(), Statement.RETURN_GENERATED_KEYS);
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				DaoHelper.processParameter(statement, parameters[i], i + 1);
			}
		}
		return statement;
	}

	protected Connection createOrReuseConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = ConnectionHandler.createConnection(dataSource);
		}
		return connection;
	}

	protected void closeConnection() {
		closeConnection(null);
	}

	protected void closeConnection(PreparedStatement statement) {
		closeConnection(statement, persistConnection);
	}

	protected void closeConnection(PreparedStatement statement, boolean persistConnection) {
		try {
			if (statement != null) {
				statement.close();
			}

			ConnectionHandler.closeConnection(connection, persistConnection);
		} catch (SQLException e) {
			// Ignore
		}
	}

	public void setPersistConnection(boolean persistConnection) {
		this.persistConnection = persistConnection;
	}

	public boolean isPersistConnection() {
		return persistConnection;
	}

	private String e(String msg, SQL sql) {
		return e(msg, sql.getSql());
	}

	private String e(String msg, String sql) {
		return msg + " [" + sql + "]";
	}
}
