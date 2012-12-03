package com.teoware.refapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.TransactionAttribute;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.dao.rowmapper.RowMapper;
import com.teoware.refapp.dao.sql.SqlStatement;
import com.teoware.refapp.dao.util.ConnectionHandler;
import com.teoware.refapp.dao.util.DaoHelper;
import com.teoware.refapp.dao.util.ResultSetExtractor;
import com.teoware.refapp.dao.util.RowMapperResultSetExtractor;

/**
 * Base DAO that holds functionality for common database operations.
 * 
 */
public abstract class BaseDao {

	private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);
	
	private static final String INSERT_ERROR_MESSAGE = "Insert operation failed.";
	private static final String UPDATE_ERROR_MESSAGE = "Update operation failed.";
	private static final String SELECT_ERROR_MESSAGE = "Select operation failed.";
	private static final String DELETE_ERROR_MESSAGE = "Delete operation failed.";

	@Resource(mappedName = "jdbc/refapp")
	protected DataSource dataSource;

	protected Connection connection;
	protected boolean persistConnection;

	protected int insert(SqlStatement sql, Object[] parameters) throws DaoException {
		try {
			return update(sql, parameters);
		} catch (DaoException e) {
			LOG.error(e(INSERT_ERROR_MESSAGE, sql));
			throw new DaoException(e(INSERT_ERROR_MESSAGE, sql), e.getCause());
		}
	}

	protected int update(SqlStatement sql, Object[] parameters) throws DaoException {
		LOG.debug("Executing SQL statement: " + sql.getSql());

		PreparedStatement statement = null;
		try {
			statement = generatePreparedStatement(sql, parameters);
			int rowsAffected = statement.executeUpdate();
			return rowsAffected;
		} catch (SQLException e) {
			LOG.error(e(UPDATE_ERROR_MESSAGE, sql));
			throw new DaoException(e(UPDATE_ERROR_MESSAGE, sql), e);
		} finally {
			closeConnection(statement);
		}
	}

	@TransactionAttribute
	protected <T> List<T> select(SqlStatement sql, RowMapper<T> rowMapper) throws DaoException {
		Object[] parameters = null;
		return select(sql, rowMapper, parameters);
	}

	@TransactionAttribute
	protected <T> List<T> select(SqlStatement sql, RowMapper<T> rowMapper, Object[] parameters) throws DaoException {
		LOG.debug("Executing SQL statement: " + sql.getSql());

		PreparedStatement statement = null;
		try {
			statement = generatePreparedStatement(sql, parameters);
			ResultSet result = statement.executeQuery();
			int rowsExpected = 0;
			ResultSetExtractor<List<T>> resultSetExtractor = new RowMapperResultSetExtractor<T>(rowMapper, rowsExpected);
			return resultSetExtractor.extractData(result);
		} catch (SQLException e) {
			LOG.error(e(SELECT_ERROR_MESSAGE, sql));
			throw new DaoException(e(SELECT_ERROR_MESSAGE, sql), e);
		} catch (ParseException e) {
			LOG.error(e(SELECT_ERROR_MESSAGE, sql));
			throw new DaoException(e(SELECT_ERROR_MESSAGE, sql), e);
		} finally {
			closeConnection(statement);
		}
	}

	protected int delete(SqlStatement sql, Object[] parameters) throws DaoException {
		try {
			return update(sql, parameters);
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
			LOG.error(e(SELECT_ERROR_MESSAGE, sql));
			throw new DaoException(e(SELECT_ERROR_MESSAGE, sql), e.getCause());
		} finally {
			closeConnection(statement);
		}
	}

	protected PreparedStatement generatePreparedStatement(SqlStatement sql, Object[] parameters) throws SQLException {
		createOrReuseConnection();
		PreparedStatement statement = connection.prepareStatement(sql.getSql());
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				DaoHelper.processParameter(statement, parameters[i], i + 1);
			}
		}
		return statement;
	}

	protected Connection createOrReuseConnection() throws SQLException {
		if (connection == null) {
			connection = ConnectionHandler.createConnection(dataSource);
		}
		return connection;
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

	protected void doCloseConnection() {
		this.persistConnection = false;
	}

	protected void doPersistConnection() {
		this.persistConnection = true;
	}
	
	private String e(String msg, SqlStatement sql) {
		return e(msg, sql.getSql());
	}
	
	private String e(String msg, String sql) {
		return msg + "[" + sql + "]";
	}
}
