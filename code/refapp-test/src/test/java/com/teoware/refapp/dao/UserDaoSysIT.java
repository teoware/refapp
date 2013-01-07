package com.teoware.refapp.dao;

import static com.teoware.refapp.dao.UserDaoBean.USERS_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_ADDRESS_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_PASSWORD_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_STATUS_TABLE;
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
import com.teoware.refapp.dao.test.TestDataSourceHandler;
import com.teoware.refapp.dao.test.UserDaoTestHelper;
import com.teoware.refapp.dao.util.SQL;
import com.teoware.refapp.model.enums.Status;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
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
			int rowsAffected = createUserJohn(userDao);

			assertEquals(4, rowsAffected);

			List<User> userList = readUser(userDao, "john.doe");

			assertNotNull(userList);
			assertEquals(1, userList.size());
			User user = userList.get(0);

			assertCreateUserJohn(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertUpdateAndSelectUserJane() {
		try {
			int rowsAffected = createUserJane(userDao);

			assertEquals(4, rowsAffected);

			List<User> userList = readUser(userDao, "jane.doe");

			assertNotNull(userList);
			assertEquals(1, userList.size());
			User user = userList.get(0);

			assertCreateUserJane(user);

			user.getUserStatus().setStatus(Status.ACTIVE);
			user.getUserInfo().setEmail("jane.doe@epost.net");
			user.getUserInfo().setPhone("+47 22334455");
			user.getUserAddress().setAddress("Nygata 2");
			user.getUserAddress().setPostalCode("1122");

			rowsAffected = updateUser(userDao, user);
			assertEquals(3, rowsAffected);

			userList = readUser(userDao, "jane.doe");

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
			int rowsAffected = createUserJonah(userDao);

			assertEquals(4, rowsAffected);

			List<UserPassword> userPasswordList = readUserPassword(userDao, "jonah.doe");

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
			int rowsAffected = createUserJohn(userDao);
			rowsAffected += createUserJane(userDao);
			rowsAffected += createUserJonah(userDao);

			assertEquals(12, rowsAffected);

			List<User> userList = readAllUsers(userDao);

			assertNotNull(userList);
			assertEquals(3, userList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertDeleteOneSelectAllUsersShouldBeTwo() {
		try {
			int rowsAffected = createUserJohn(userDao);
			rowsAffected += createUserJane(userDao);
			rowsAffected += createUserJonah(userDao);

			assertEquals(12, rowsAffected);

			rowsAffected = deleteUser(userDao, "john.doe");

			assertEquals(1, rowsAffected);

			List<User> userList = readAllUsers(userDao);

			assertNotNull(userList);
			assertEquals(2, userList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void cleanTables() throws DaoException {
		if (userDao.rowCount(USERS_TABLE) > 0) {
			userDao.delete(new SQL("DELETE FROM " + USERS_TABLE));
		}

		if (userDao.rowCount(USER_STATUS_TABLE) > 0) {
			userDao.delete(new SQL("DELETE FROM " + USER_STATUS_TABLE));
		}

		if (userDao.rowCount(USER_ADDRESS_TABLE) > 0) {
			userDao.delete(new SQL("DELETE FROM " + USER_ADDRESS_TABLE));
		}

		if (userDao.rowCount(USER_PASSWORD_TABLE) > 0) {
			userDao.delete(new SQL("DELETE FROM " + USER_PASSWORD_TABLE));
		}
	}
}
