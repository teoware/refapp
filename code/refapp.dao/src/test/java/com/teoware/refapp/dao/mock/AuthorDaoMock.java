package com.teoware.refapp.dao.mock;

import java.sql.SQLException;

import bitronix.tm.resource.jdbc.PoolingDataSource;

import com.teoware.refapp.dao.impl.AuthorDaoImpl;

public class AuthorDaoMock extends AuthorDaoImpl {

	private static final long serialVersionUID = 1L;

	public AuthorDaoMock() {
		createOrReuseDataSourceForTest();
	}

	private void createOrReuseDataSourceForTest() {
		if (super.dataSource == null || !(super.dataSource instanceof PoolingDataSource)) {
			super.dataSource = createDataSourceForTest();
		}
	}

	private static PoolingDataSource createDataSourceForTest() {
		PoolingDataSource dataSource = new PoolingDataSource();
		try {
			dataSource.setClassName("org.apache.derby.jdbc.ClientXADataSource");
			dataSource.setUniqueName("refappTestDataSource");
			dataSource.setMaxPoolSize(5);
			dataSource.setAllowLocalTransactions(true);
			dataSource.setAutomaticEnlistingEnabled(true);
			dataSource.setTestQuery("SELECT 1 FROM SYS.SYSSCHEMAS");
			dataSource.getDriverProperties().setProperty("User", "refapp");
			dataSource.getDriverProperties().setProperty("Password", "abcd1234");
			dataSource.getDriverProperties().setProperty("ServerName", "localhost");
			dataSource.getDriverProperties().setProperty("PortNumber", "1527");
			dataSource.getDriverProperties().setProperty("DatabaseName", "refapp");
			dataSource.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSource;
	}

	@Override
	protected boolean isPersistConnection() {
		return true;
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
