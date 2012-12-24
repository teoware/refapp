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
import com.teoware.refapp.model.enums.UserStatus;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.util.PasswordHandler;

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
		int rowsAffected = insertUserJohn(authorDao);

		assertEquals(3, rowsAffected);

		List<User> authorList = selectUserJohn(authorDao);

		assertNotNull(authorList);
		assertEquals(1, authorList.size());
		User author = authorList.get(0);

		assertInsertUserJohn(author);
	}

	@Test
	public void testInsertUpdateAndSelectUserJane() throws DaoException {
		int rowsAffected = insertUserJane(authorDao);

		assertEquals(3, rowsAffected);

		List<User> authorList = selectUserJane(authorDao);

		assertNotNull(authorList);
		assertEquals(1, authorList.size());
		User author = authorList.get(0);

		assertInsertUserJane(author);

		author.getUserId().setStatus(UserStatus.ACTIVE);
		author.getUserInfo().setEmail("jane.doe@epost.net");
		author.getUserInfo().setPhone("+47 22334455");
		author.getUserAddress().setAddress("Nygata 2");
		author.getUserAddress().setPostalCode("1122");

		rowsAffected = updateUser(authorDao, author, null);
		assertEquals(3, rowsAffected);

		authorList = selectUserJane(authorDao);

		assertNotNull(authorList);
		assertEquals(1, authorList.size());
		author = authorList.get(0);

		assertUpdateUserJane(author);
	}

	@Test
	public void testInsertAndSelectPasswordForUserJonah() throws DaoException {
		int rowsAffected = insertUserJonah(authorDao);

		assertEquals(3, rowsAffected);

		List<UserPassword> authorPasswordList = selectUserPasswordJonah(authorDao);

		assertNotNull(authorPasswordList);
		assertEquals(1, authorPasswordList.size());
		UserPassword authorPassword = authorPasswordList.get(0);

		assertNotNull(authorPassword.getPassword());
		assertTrue(PasswordHandler.verifyPassword("jonahsPassword", authorPassword.getPassword()));
	}

	@Test
	public void testInsertAndSelectAllUsersShouldBeThree() throws DaoException {
		int rowsAffected = insertUserJohn(authorDao);
		rowsAffected += insertUserJane(authorDao);
		rowsAffected += insertUserJonah(authorDao);

		assertEquals(9, rowsAffected);

		List<User> authorList = selectAllUsers(authorDao);

		assertNotNull(authorList);
		assertEquals(3, authorList.size());
	}

	@Test
	public void testInsertDeleteOneAndSelectAllUsersShouldBeTwo() throws DaoException {
		int rowsAffected = insertUserJohn(authorDao);
		rowsAffected += insertUserJane(authorDao);
		rowsAffected += insertUserJonah(authorDao);

		assertEquals(9, rowsAffected);

		rowsAffected = deleteUserJohn(authorDao);

		assertEquals(1, rowsAffected);

		List<User> authorList = selectAllUsers(authorDao);

		assertNotNull(authorList);
		assertEquals(2, authorList.size());
	}

	private static void cleanTables() throws DaoException {
		if (authorDao.rowCount(USERS_TABLE) > 0) {
			authorDao.delete(new SqlStatement("DELETE FROM " + USERS_TABLE), null);
		}

		if (authorDao.rowCount(USERS_STATUS_TABLE) > 0) {
			authorDao.delete(new SqlStatement("DELETE FROM " + USERS_STATUS_TABLE), null);
		}

		if (authorDao.rowCount(USERS_ADDRESS_TABLE) > 0) {
			authorDao.delete(new SqlStatement("DELETE FROM " + USERS_ADDRESS_TABLE), null);
		}

		if (authorDao.rowCount(USERS_PASSWORD_TABLE) > 0) {
			authorDao.delete(new SqlStatement("DELETE FROM " + USERS_PASSWORD_TABLE), null);
		}
	}
}
