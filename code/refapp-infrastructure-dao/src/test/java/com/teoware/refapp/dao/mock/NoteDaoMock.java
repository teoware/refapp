package com.teoware.refapp.dao.mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.teoware.refapp.dao.NoteDaoBean;

public class NoteDaoMock extends NoteDaoBean {

	private static final long serialVersionUID = 1L;

	public NoteDaoMock(Connection connection) {
		super.setConnection(connection);
	}

	@Override
	protected Connection createOrReuseConnection() throws SQLException {
		return super.getConnection();
	}

	@Override
	protected void closeConnection(ResultSet result, PreparedStatement statement, boolean persistConnection) {
		try {
			if (result != null) {
				result.close();
			}

			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			// Ignore
		}
	}

	public void closeAll() throws SQLException {
		if (super.getConnection() != null) {
			super.getConnection().close();
		}
	}
}
