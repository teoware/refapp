package com.teoware.refapp.dao;

import static com.teoware.refapp.dao.UserDaoBean.USERS_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_ADDRESS_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_PASSWORD_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_STATUS_TABLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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

@Category(com.teoware.refapp.test.IntegrationTestGroup.class)
public class UserDaoIT extends UserDaoTestHelper {

	private static Connection connection;
	private static UserDaoMock userDao;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		connection = TestDataSourceHandler.initializeDatabase();
		userDao = new UserDaoMock(connection);
	}

	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		userDao.closeAll();
	}

	@Before
	public void setUp() throws Exception {
		cleanTables();
	}

	@Test
	public void testInsertAndSelectUserJohn() {
		try {
			int rowsAffected;

			rowsAffected = createUserJohn(userDao);

			assertEquals(4, rowsAffected);

			List<User> authorList = readUserJohn(userDao);

			assertNotNull(authorList);
			assertEquals(1, authorList.size());
			User author = authorList.get(0);

			assertCreateUserJohn(author);
		} catch (DaoException e) {
			e.printStackTrace();
			fail("" + e.getMessage());
		}
	}

	@Test
	public void testInsertUpdateAndSelectUserJane() throws DaoException {
		int rowsAffected = createUserJane(userDao);

		assertEquals(4, rowsAffected);

		List<User> userList = readUserJane(userDao);

		assertNotNull(userList);
		assertEquals(1, userList.size());
		User user = userList.get(0);

		assertCreateUserJane(user);

		user.getUserInfo().setEmail("jane.doe@epost.net");
		user.getUserInfo().setPhone("+47 22334455");
		user.getUserAddress().setAddress("Nygata 2");
		user.getUserAddress().setPostalCode("1122");
		user.getUserStatus().setStatus(Status.ACTIVE);

		rowsAffected = updateUser(userDao, user, null);
		assertEquals(3, rowsAffected);

		userList = readUserJane(userDao);

		assertNotNull(userList);
		assertEquals(1, userList.size());
		user = userList.get(0);

		assertUpdateUserJane(user);
	}

	@Test
	public void testInsertAndSelectPasswordForUserJonah() throws DaoException {
		int rowsAffected = createUserJonah(userDao);

		assertEquals(4, rowsAffected);

		List<UserPassword> userPasswordList = readUserPasswordJonah(userDao);

		assertNotNull(userPasswordList);
		assertEquals(1, userPasswordList.size());
		UserPassword authorPassword = userPasswordList.get(0);

		assertNotNull(authorPassword.getPassword());
		assertEquals("jonahsPassword", authorPassword.getPassword());
		assertEquals("jonahsPasswordSalt", authorPassword.getSalt());
	}

	@Test
	public void testInsertAndSelectAllUsersShouldBeThree() throws DaoException {
		int rowsAffected = createUserJohn(userDao);
		rowsAffected += createUserJane(userDao);
		rowsAffected += createUserJonah(userDao);

		assertEquals(12, rowsAffected);

		List<User> userList = readAllUsers(userDao);

		assertNotNull(userList);
		assertEquals(3, userList.size());
	}

	@Test
	public void testInsertDeleteOneAndSelectAllUsersShouldBeTwo() throws DaoException {
		int rowsAffected = createUserJohn(userDao);
		rowsAffected += createUserJane(userDao);
		rowsAffected += createUserJonah(userDao);

		assertEquals(12, rowsAffected);

		rowsAffected = deleteUserJohn(userDao);

		assertEquals(1, rowsAffected);

		List<User> userList = readAllUsers(userDao);

		assertNotNull(userList);
		assertEquals(2, userList.size());
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
