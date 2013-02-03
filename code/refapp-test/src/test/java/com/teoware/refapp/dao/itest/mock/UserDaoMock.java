package com.teoware.refapp.dao.itest.mock;

import static com.teoware.refapp.dao.metadata.UserTables.ID_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.USERNAME_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.UserTables.USER_ID_COLUMN_NAME;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.UserDaoBean;
import com.teoware.refapp.dao.rowmapper.UserIdRowMapper;
import com.teoware.refapp.dao.util.DaoHelper;
import com.teoware.refapp.dao.util.SQL;
import com.teoware.refapp.model.common.Id;

public class UserDaoMock extends UserDaoBean {

	private static final long serialVersionUID = 1L;

	public UserDaoMock(Connection connection) {
		super.connection = connection;
	}

	@Override
	protected Connection createOrReuseConnection() throws SQLException {
		return super.connection;
	}

	public void closeAll() throws SQLException {
		rollBack();
		if (super.connection != null) {
			super.connection.close();
		}
	}

	public void rollBack() throws SQLException {
		if (super.connection != null) {
			super.connection.rollback();
		}
	}

	public void cleanAll() throws DaoException {
		List<Id> users = super.read(
				new SQL.Builder().doSelect(ID_COLUMN_NAME).from(USERS_TABLE).whereLike(USERNAME_COLUMN_NAME).build(),
				new UserIdRowMapper(), DaoHelper.generateArray("%.doe"));
		Object[] ids = new Object[users.size()];
		for (int i = 0; i < users.size(); i++) {
			ids[i] = users.get(i).getId();
			System.out.println("Hey: " + ids[i]);
		}

		super.delete(new SQL.Builder().doDelete(USER_PASSWORD_TABLE).whereIn(USER_ID_COLUMN_NAME, 3).build(), ids);
		super.delete(new SQL.Builder().doDelete(USER_ADDRESS_TABLE).whereIn(USER_ID_COLUMN_NAME, 3).build(), ids);
		super.delete(new SQL.Builder().doDelete(USER_DETAILS_TABLE).whereIn(USER_ID_COLUMN_NAME, 3).build(), ids);
		super.delete(new SQL.Builder().doDelete(USER_STATUS_TABLE).whereIn(USER_ID_COLUMN_NAME, 3).build(), ids);
		super.delete(new SQL.Builder().doDelete(USERS_TABLE).whereIn(USER_ID_COLUMN_NAME, 3).build(), ids);
	}
}
