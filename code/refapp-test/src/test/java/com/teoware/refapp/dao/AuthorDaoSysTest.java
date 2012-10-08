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

import org.junit.After;
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

@Category(com.teoware.refapp.test.SystemTestGroup.class)
public class AuthorDaoSysTest extends AuthorDaoTestHelper {

	private static Connection connection;
	private static AuthorDaoMock authorDao;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		connection = TestDataSourceHandler.createDataSourceConnection();
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

	@After
	public void tearDown() throws Exception {
		cleanTables();
	}

	@Test
	public void testInsertAndSelectAuthorJohn() {
		try {
			int rowsAffected = insertAuthorJohn(authorDao);

			assertEquals(4, rowsAffected);

			List<Author> authorList = selectAuthor(authorDao, "john.doe");

			assertNotNull(authorList);
			assertEquals(1, authorList.size());
			Author author = authorList.get(0);

			assertInsertAuthorJohn(author);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertUpdateAndSelectAuthorJane() {
		try {
			int rowsAffected = insertAuthorJane(authorDao);

			assertEquals(4, rowsAffected);

			List<Author> authorList = selectAuthor(authorDao, "jane.doe");

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

			authorList = selectAuthor(authorDao, "jane.doe");

			assertNotNull(authorList);
			assertEquals(1, authorList.size());
			author = authorList.get(0);

			assertUpdateAuthorJane(author);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertAuthorJonahAndSelectPassword() {
		try {
			int rowsAffected = insertAuthorJonah(authorDao);

			assertEquals(4, rowsAffected);

			List<AuthorPassword> authorPasswordList = selectAuthorPassword(authorDao, "jonah.doe");

			assertNotNull(authorPasswordList);
			assertEquals(1, authorPasswordList.size());
			AuthorPassword authorPassword = authorPasswordList.get(0);

			assertNotNull(authorPassword.getPassword());
			assertTrue(PasswordHandler.verifyPassword("jonahsPassword", authorPassword.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertAndSelectAllAuthorsShouldBeThree() {
		try {
			int rowsAffected = insertAuthorJohn(authorDao);
			rowsAffected += insertAuthorJane(authorDao);
			rowsAffected += insertAuthorJonah(authorDao);

			assertEquals(12, rowsAffected);

			List<Author> authorList = selectAllAuthors(authorDao);

			assertNotNull(authorList);
			assertEquals(3, authorList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertDeleteOneSelectAllAuthorsShouldBeTwo() {
		try {
			int rowsAffected = insertAuthorJohn(authorDao);
			rowsAffected += insertAuthorJane(authorDao);
			rowsAffected += insertAuthorJonah(authorDao);

			assertEquals(12, rowsAffected);

			rowsAffected = deleteAuthor(authorDao, "john.doe");

			assertEquals(1, rowsAffected);

			List<Author> authorList = selectAllAuthors(authorDao);

			assertNotNull(authorList);
			assertEquals(2, authorList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void cleanTables() {
		try {
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
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
}
