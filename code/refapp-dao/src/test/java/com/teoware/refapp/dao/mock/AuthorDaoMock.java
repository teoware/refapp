package com.teoware.refapp.dao.mock;

import java.sql.Connection;
import java.sql.SQLException;

import com.teoware.refapp.dao.AuthorDaoBean;
import com.teoware.refapp.dao.test.TestDataSourceHandler;

public class AuthorDaoMock extends AuthorDaoBean {

	private static final long serialVersionUID = 1L;

	private Connection connection;

	@Override
	protected Connection createOrReuseConnection() throws SQLException {
		try {
			this.connection = TestDataSourceHandler.createJdbcConnection();
			super.connection = this.connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.connection;
	}

	public void closeAll() throws SQLException {
		if (this.connection != null) {
			this.connection.close();
		}
	}
}
