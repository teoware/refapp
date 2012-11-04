package com.teoware.refapp.dao;

import static com.teoware.refapp.dao.AuthorDaoBean.AUTHORS_ADDRESS_TABLE;
import static com.teoware.refapp.dao.AuthorDaoBean.AUTHORS_PASSWORD_TABLE;
import static com.teoware.refapp.dao.AuthorDaoBean.AUTHORS_STATUS_TABLE;
import static com.teoware.refapp.dao.AuthorDaoBean.AUTHORS_TABLE;
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

import com.teoware.refapp.dao.mock.AuthorDaoMock;
import com.teoware.refapp.dao.sql.SqlStatement;
import com.teoware.refapp.dao.test.AuthorDaoTestHelper;
import com.teoware.refapp.dao.test.TestDataSourceHandler;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.util.PasswordHandler;

@Category(com.teoware.refapp.test.IntegrationTestGroup.class)
public class AuthorDaoIT extends AuthorDaoTestHelper {

	private static Connection connection;
	private static AuthorDaoMock authorDao;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		connection = TestDataSourceHandler.initializeDatabase();
		authorDao = new AuthorDaoMock(connection);
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
	public void testInsertAndSelectAuthorJohn() throws DaoException {
		int rowsAffected = insertAuthorJohn(authorDao);

		assertEquals(3, rowsAffected);

		List<Author> authorList = selectAuthorJohn(authorDao);

		assertNotNull(authorList);
		assertEquals(1, authorList.size());
		Author author = authorList.get(0);

		assertInsertAuthorJohn(author);
	}

	@Test
	public void testInsertUpdateAndSelectAuthorJane() throws DaoException {
		int rowsAffected = insertAuthorJane(authorDao);

		assertEquals(3, rowsAffected);

		List<Author> authorList = selectAuthorJane(authorDao);

		assertNotNull(authorList);
		assertEquals(1, authorList.size());
		Author author = authorList.get(0);

		assertInsertAuthorJane(author);

		author.getAuthorId().setStatus(AuthorStatus.ACTIVE);
		author.getAuthorInfo().setEmail("jane.doe@epost.net");
		author.getAuthorInfo().setPhone("+47 22334455");
		author.getAuthorAddress().setAddress("Nygata 2");
		author.getAuthorAddress().setPostalCode("1122");

		rowsAffected = updateAuthor(authorDao, author, null);
		assertEquals(3, rowsAffected);

		authorList = selectAuthorJane(authorDao);

		assertNotNull(authorList);
		assertEquals(1, authorList.size());
		author = authorList.get(0);

		assertUpdateAuthorJane(author);
	}

	@Test
	public void testInsertAndSelectPasswordForAuthorJonah() throws DaoException {
		int rowsAffected = insertAuthorJonah(authorDao);

		assertEquals(3, rowsAffected);

		List<AuthorPassword> authorPasswordList = selectAuthorPasswordJonah(authorDao);

		assertNotNull(authorPasswordList);
		assertEquals(1, authorPasswordList.size());
		AuthorPassword authorPassword = authorPasswordList.get(0);

		assertNotNull(authorPassword.getPassword());
		assertTrue(PasswordHandler.verifyPassword("jonahsPassword", authorPassword.getPassword()));
	}

	@Test
	public void testInsertAndSelectAllAuthorsShouldBeThree() throws DaoException {
		int rowsAffected = insertAuthorJohn(authorDao);
		rowsAffected += insertAuthorJane(authorDao);
		rowsAffected += insertAuthorJonah(authorDao);

		assertEquals(9, rowsAffected);

		List<Author> authorList = selectAllAuthors(authorDao);

		assertNotNull(authorList);
		assertEquals(3, authorList.size());
	}

	@Test
	public void testInsertDeleteOneAndSelectAllAuthorsShouldBeTwo() throws DaoException {
		int rowsAffected = insertAuthorJohn(authorDao);
		rowsAffected += insertAuthorJane(authorDao);
		rowsAffected += insertAuthorJonah(authorDao);

		assertEquals(9, rowsAffected);

		rowsAffected = deleteAuthorJohn(authorDao);

		assertEquals(1, rowsAffected);

		List<Author> authorList = selectAllAuthors(authorDao);

		assertNotNull(authorList);
		assertEquals(2, authorList.size());
	}

	private static void cleanTables() throws DaoException {
		if (authorDao.rowCount(AUTHORS_TABLE) > 0) {
			authorDao.delete(new SqlStatement("DELETE FROM " + AUTHORS_TABLE), null);
		}

		if (authorDao.rowCount(AUTHORS_STATUS_TABLE) > 0) {
			authorDao.delete(new SqlStatement("DELETE FROM " + AUTHORS_STATUS_TABLE), null);
		}

		if (authorDao.rowCount(AUTHORS_ADDRESS_TABLE) > 0) {
			authorDao.delete(new SqlStatement("DELETE FROM " + AUTHORS_ADDRESS_TABLE), null);
		}

		if (authorDao.rowCount(AUTHORS_PASSWORD_TABLE) > 0) {
			authorDao.delete(new SqlStatement("DELETE FROM " + AUTHORS_PASSWORD_TABLE), null);
		}
	}
}
