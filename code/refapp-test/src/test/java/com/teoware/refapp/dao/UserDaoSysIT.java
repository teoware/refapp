package com.teoware.refapp.dao;

import static com.teoware.refapp.dao.UserDaoBean.USERS_ADDRESS_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USERS_PASSWORD_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USERS_STATUS_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USERS_TABLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.teoware.refapp.dao.mock.UserDaoMock;
import com.teoware.refapp.dao.sql.SqlStatement;
import com.teoware.refapp.dao.test.UserDaoTestHelper;
import com.teoware.refapp.dao.test.TestDataSourceHandler;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.enums.UserStatus;
import com.teoware.refapp.test.SystemTestGroup;
import com.teoware.refapp.util.PasswordHandler;

@Category(SystemTestGroup.class)
public class UserDaoSysIT extends UserDaoTestHelper {

	private static Connection connection;
	private static UserDaoMock userDao;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		connection = TestDataSourceHandler.createDataSourceConnection();
		userDao = new UserDaoMock(connection);
	}

	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		cleanTables();
		userDao.closeAll();
	}

	@Before
	public void setUp() throws Exception {
		cleanTables();
	}

	@Test
	public void testInsertAndSelectUserJohn() {
		try {
			int rowsAffected = insertUserJohn(userDao);

			assertEquals(4, rowsAffected);

			List<User> userList = selectUser(userDao, "john.doe");

			assertNotNull(userList);
			assertEquals(1, userList.size());
			User user = userList.get(0);

			assertInsertUserJohn(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertUpdateAndSelectUserJane() {
		try {
			int rowsAffected = insertUserJane(userDao);

			assertEquals(4, rowsAffected);

			List<User> userList = selectUser(userDao, "jane.doe");

			assertNotNull(userList);
			assertEquals(1, userList.size());
			User user = userList.get(0);

			assertInsertUserJane(user);

			user.getUserId().setStatus(UserStatus.ACTIVE);
			user.getUserInfo().setEmail("jane.doe@epost.net");
			user.getUserInfo().setPhone("+47 22334455");
			user.getUserAddress().setAddress("Nygata 2");
			user.getUserAddress().setPostalCode("1122");

			rowsAffected = updateUser(userDao, user, null);
			assertEquals(3, rowsAffected);

			userList = selectUser(userDao, "jane.doe");

			assertNotNull(userList);
			assertEquals(1, userList.size());
			user = userList.get(0);

			assertUpdateUserJane(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertUserJonahAndSelectPassword() {
		try {
			int rowsAffected = insertUserJonah(userDao);

			assertEquals(4, rowsAffected);

			List<UserPassword> userPasswordList = selectUserPassword(userDao, "jonah.doe");

			assertNotNull(userPasswordList);
			assertEquals(1, userPasswordList.size());
			UserPassword userPassword = userPasswordList.get(0);

			assertNotNull(userPassword.getPassword());
			assertTrue(PasswordHandler.verifyPassword("jonahsPassword", userPassword.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertAndSelectAllUsersShouldBeThree() {
		try {
			int rowsAffected = insertUserJohn(userDao);
			rowsAffected += insertUserJane(userDao);
			rowsAffected += insertUserJonah(userDao);

			assertEquals(12, rowsAffected);

			List<User> userList = selectAllUsers(userDao);

			assertNotNull(userList);
			assertEquals(3, userList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertDeleteOneSelectAllUsersShouldBeTwo() {
		try {
			int rowsAffected = insertUserJohn(userDao);
			rowsAffected += insertUserJane(userDao);
			rowsAffected += insertUserJonah(userDao);

			assertEquals(12, rowsAffected);

			rowsAffected = deleteUser(userDao, "john.doe");

			assertEquals(1, rowsAffected);

			List<User> userList = selectAllUsers(userDao);

			assertNotNull(userList);
			assertEquals(2, userList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void cleanTables() throws DaoException {
		if (userDao.rowCount(USERS_TABLE) > 0) {
			userDao.delete(new SqlStatement("DELETE FROM " + USERS_TABLE), null);
		}

		if (userDao.rowCount(USERS_STATUS_TABLE) > 0) {
			userDao.delete(new SqlStatement("DELETE FROM " + USERS_STATUS_TABLE), null);
		}

		if (userDao.rowCount(USERS_ADDRESS_TABLE) > 0) {
			userDao.delete(new SqlStatement("DELETE FROM " + USERS_ADDRESS_TABLE), null);
		}

		if (userDao.rowCount(USERS_PASSWORD_TABLE) > 0) {
			userDao.delete(new SqlStatement("DELETE FROM " + USERS_PASSWORD_TABLE), null);
		}
	}
}
