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

public abstract class BaseDao {

	private static final Logger LOG = LoggerFactory.getLogger(BaseDao.class);

	@Resource(mappedName = "jdbc/refapp")
	protected DataSource dataSource;

	protected Connection connection;
	protected boolean persistConnection;

	protected int insert(SqlStatement sql, Object[] parameters) throws DaoException {
		try {
			return update(sql, parameters);
		} catch (DaoException e) {
			LOG.error("Insert operation failed.");
			throw new DaoException("Insert operation failed.", e.getCause());
		}
	}

	protected int update(SqlStatement sql, Object[] parameters) throws DaoException {
		LOG.debug("Executing SQL statement: " + sql.build());

		PreparedStatement statement = null;
		try {
			statement = generatePreparedStatement(sql, parameters);
			int rowsAffected = statement.executeUpdate();
			return rowsAffected;
		} catch (SQLException e) {
			LOG.error("Update operation failed.");
			throw new DaoException("Update operation failed.", e);
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
		LOG.debug("Executing SQL statement: " + sql.build());

		PreparedStatement statement = null;
		try {
			statement = generatePreparedStatement(sql, parameters);
			ResultSet result = statement.executeQuery();
			int rowsExpected = 0;
			ResultSetExtractor<List<T>> resultSetExtractor = new RowMapperResultSetExtractor<T>(rowMapper, rowsExpected);
			return resultSetExtractor.extractData(result);
		} catch (SQLException e) {
			LOG.error("Select operation failed.");
			throw new DaoException("Select operation failed.", e);
		} catch (ParseException e) {
			LOG.error("Select operation failed.");
			throw new DaoException("Select operation failed.", e);
		} finally {
			closeConnection(statement);
		}
	}

	protected int delete(SqlStatement sql, Object[] parameters) throws DaoException {
		try {
			return update(sql, parameters);
		} catch (DaoException e) {
			LOG.error("Delete operation failed.");
			throw new DaoException("Delete operation failed.", e.getCause());
		}
	}

	protected PreparedStatement generatePreparedStatement(SqlStatement sql, Object[] parameters) throws SQLException {
		createOrReuseConnection();
		PreparedStatement statement = connection.prepareStatement(sql.build());
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
}
