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

import com.teoware.refapp.dao.rowmapper.RowMapper;
import com.teoware.refapp.dao.sql.SqlStatement;
import com.teoware.refapp.dao.util.ConnectionHandler;
import com.teoware.refapp.dao.util.DaoHelper;
import com.teoware.refapp.dao.util.ResultSetExtractor;
import com.teoware.refapp.dao.util.RowMapperResultSetExtractor;

public abstract class BaseDao<T> {

	@Resource(mappedName = "jdbc/refapp")
	private DataSource dataSource;
	private Connection connection;

	public DataSource getDataSource() {
		return dataSource;
	}

	private Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = ConnectionHandler.createConnection(dataSource);
		}
		return connection;
	}

	protected int insert(SqlStatement sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	protected int update(SqlStatement sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@TransactionAttribute
	protected List<T> select(SqlStatement sql, RowMapper<T> rowMapper) throws DaoException {
		Object[] parameters = null;
		return select(sql, rowMapper, parameters);
	}

	@TransactionAttribute
	protected List<T> select(SqlStatement sql, RowMapper<T> rowMapper, Object[] parameters) throws DaoException {
		PreparedStatement statement = null;
		try {
			statement = generatePreparedStatement(sql, parameters);
			ResultSet result = statement.executeQuery();
			int rowsExpected = 0;
			ResultSetExtractor<List<T>> resultSetExtractor = new RowMapperResultSetExtractor<T>(rowMapper, rowsExpected);
			return resultSetExtractor.extractData(result);
		} catch (SQLException e) {
			throw new DaoException("Select operation failed.", e);
		} catch (ParseException e) {
			throw new DaoException("Select operation failed.", e);
		} finally {
			closeConnection(statement);
		}
	}

	protected int delete(SqlStatement sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	private PreparedStatement generatePreparedStatement(SqlStatement sql, Object[] parameters) throws SQLException {
		PreparedStatement statement = getConnection().prepareStatement(sql.getStatement());
		if (parameters != null) {
			for (int i = 0; i < parameters.length; i++) {
				DaoHelper.processParameter(statement, parameters[i], i+1);
			}
		}
		return statement;
	}

	private void closeConnection(PreparedStatement statement) {
		 boolean persistConnection = false;
		closeConnection(statement, persistConnection);
	}

	private void closeConnection(PreparedStatement statement, boolean persistConnection) {
		try {
			if (statement != null) {
				statement.close();
			}
			
			ConnectionHandler.closeConnection(connection, persistConnection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
