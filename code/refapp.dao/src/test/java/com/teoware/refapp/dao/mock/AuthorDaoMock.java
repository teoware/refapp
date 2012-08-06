package com.teoware.refapp.dao.mock;

import bitronix.tm.resource.jdbc.PoolingDataSource;

import com.teoware.refapp.dao.impl.AuthorDaoImpl;

public class AuthorDaoMock extends AuthorDaoImpl {

	private static final long serialVersionUID = 1L;

	public AuthorDaoMock() {
		this.dataSource = createDataSourceForTest();
	}

	private static PoolingDataSource createDataSourceForTest() {
		PoolingDataSource dataSource = new PoolingDataSource();
		dataSource.setClassName("org.apache.derby.jdbc.ClientXADataSource");
		dataSource.setUniqueName("refappDataSource");
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
		return dataSource;
	}
}
