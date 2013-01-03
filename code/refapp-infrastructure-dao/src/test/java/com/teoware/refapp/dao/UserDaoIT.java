package com.teoware.refapp.dao;

import static com.teoware.refapp.dao.UserDaoBean.USERS_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_ADDRESS_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_PASSWORD_TABLE;
import static com.teoware.refapp.dao.UserDaoBean.USER_STATUS_TABLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.teoware.refapp.model.enums.UserStatus;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;

@Category(com.teoware.refapp.test.IntegrationTestGroup.class)
public class UserDaoIT extends UserDaoTestHelper {

	private static Connection connection;
	private static UserDaoMock authorDao;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		connection = TestDataSourceHandler.initializeDatabase();
		authorDao = new UserDaoMock(connection);
	}

	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		authorDao.closeAll();
	}

	@Before
	public void setUp() throws Exception {
		cleanTables();
	}

	@Test
	public void testInsertAndSelectUserJohn() throws DaoException {
		int rowsAffected;

		rowsAffected = createUserJohn(authorDao);

		assertEquals(4, rowsAffected);

		List<User> authorList = readUserJohn(authorDao);

		assertNotNull(authorList);
		assertEquals(1, authorList.size());
		User author = authorList.get(0);

		assertCreateUserJohn(author);
	}

	@Test
	public void testInsertUpdateAndSelectUserJane() throws DaoException {
		int rowsAffected = createUserJane(authorDao);

		assertEquals(4, rowsAffected);

		List<User> authorList = readUserJane(authorDao);

		assertNotNull(authorList);
		assertEquals(1, authorList.size());
		User author = authorList.get(0);

		assertCreateUserJane(author);

		author.getUserId().setStatus(UserStatus.ACTIVE);
		author.getUserInfo().setEmail("jane.doe@epost.net");
		author.getUserInfo().setPhone("+47 22334455");
		author.getUserAddress().setAddress("Nygata 2");
		author.getUserAddress().setPostalCode("1122");

		rowsAffected = updateUser(authorDao, author, null);
		assertEquals(3, rowsAffected);

		authorList = readUserJane(authorDao);

		assertNotNull(authorList);
		assertEquals(1, authorList.size());
		author = authorList.get(0);

		assertUpdateUserJane(author);
	}

	@Test
	public void testInsertAndSelectPasswordForUserJonah() throws DaoException {
		int rowsAffected = createUserJonah(authorDao);

		assertEquals(4, rowsAffected);

		List<UserPassword> authorPasswordList = readUserPasswordJonah(authorDao);

		assertNotNull(authorPasswordList);
		assertEquals(1, authorPasswordList.size());
		UserPassword authorPassword = authorPasswordList.get(0);

		assertNotNull(authorPassword.getPassword());
		assertEquals("jonahsPassword", authorPassword.getPassword());
		assertEquals("jonahsPasswordSalt", authorPassword.getSalt());
	}

	@Test
	public void testInsertAndSelectAllUsersShouldBeThree() throws DaoException {
		int rowsAffected = createUserJohn(authorDao);
		rowsAffected += createUserJane(authorDao);
		rowsAffected += createUserJonah(authorDao);

		assertEquals(12, rowsAffected);

		List<User> authorList = readAllUsers(authorDao);

		assertNotNull(authorList);
		assertEquals(3, authorList.size());
	}

	@Test
	public void testInsertDeleteOneAndSelectAllUsersShouldBeTwo() throws DaoException {
		int rowsAffected = createUserJohn(authorDao);
		rowsAffected += createUserJane(authorDao);
		rowsAffected += createUserJonah(authorDao);

		assertEquals(12, rowsAffected);

		rowsAffected = deleteUserJohn(authorDao);

		assertEquals(1, rowsAffected);

		List<User> authorList = readAllUsers(authorDao);

		assertNotNull(authorList);
		assertEquals(2, authorList.size());
	}

	private static void cleanTables() throws DaoException {
		if (authorDao.rowCount(USERS_TABLE) > 0) {
			authorDao.delete(new SQL("DELETE FROM " + USERS_TABLE), null);
		}

		if (authorDao.rowCount(USER_STATUS_TABLE) > 0) {
			authorDao.delete(new SQL("DELETE FROM " + USER_STATUS_TABLE), null);
		}

		if (authorDao.rowCount(USER_ADDRESS_TABLE) > 0) {
			authorDao.delete(new SQL("DELETE FROM " + USER_ADDRESS_TABLE), null);
		}

		if (authorDao.rowCount(USER_PASSWORD_TABLE) > 0) {
			authorDao.delete(new SQL("DELETE FROM " + USER_PASSWORD_TABLE), null);
		}
	}
}
