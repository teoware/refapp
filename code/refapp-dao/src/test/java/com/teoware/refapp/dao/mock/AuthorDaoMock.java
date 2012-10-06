package com.teoware.refapp.dao.mock;

import java.sql.SQLException;

import bitronix.tm.resource.jdbc.PoolingDataSource;

import com.teoware.refapp.dao.AuthorDaoBean;
import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.test.TestDataSourceHandler;

public class AuthorDaoMock extends AuthorDaoBean {

	private static final long serialVersionUID = 1L;

	public AuthorDaoMock() {
		try {
			createOrReuseDataSourceForTest();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createOrReuseDataSourceForTest() throws DaoException {
		if (super.dataSource == null || !(super.dataSource instanceof PoolingDataSource)) {
			super.dataSource = TestDataSourceHandler.createDataSourceForTest();
		}
	}

	public void closeAll() {
		try {
			if (super.connection != null) {
				super.connection.close();
			}
			if (super.dataSource != null && super.dataSource instanceof PoolingDataSource) {
				((PoolingDataSource) super.dataSource).close();
			}
		} catch (SQLException e) {
			// Ignore
		}
	}
}
