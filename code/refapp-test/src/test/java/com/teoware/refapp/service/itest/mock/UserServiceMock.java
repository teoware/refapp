package com.teoware.refapp.service.itest.mock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.teoware.refapp.dao.itest.mock.UserDaoMock;
import com.teoware.refapp.dao.itest.util.TestDataSourceHandler;
import com.teoware.refapp.service.UserServiceBean;

public class UserServiceMock extends UserServiceBean {

	private static final long serialVersionUID = 1L;

	public UserServiceMock() throws FileNotFoundException, ClassNotFoundException, SQLException, IOException {
		Connection connection = TestDataSourceHandler.createDataSourceConnection();
		super.userDao = new UserDaoMock(connection);
	}

	public void terminateConnection() {
		super.userDao.terminateConnection();
	}
}
