package com.teoware.refapp.dao;

import static com.teoware.refapp.dao.AuthorDaoBean.AUTHORS_ADDRESS_TABLE;
import static com.teoware.refapp.dao.AuthorDaoBean.AUTHORS_PASSWORD_TABLE;
import static com.teoware.refapp.dao.AuthorDaoBean.AUTHORS_STATUS_TABLE;
import static com.teoware.refapp.dao.AuthorDaoBean.AUTHORS_TABLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.teoware.refapp.dao.dto.DeleteAuthorRequest;
import com.teoware.refapp.dao.dto.DeleteAuthorResponse;
import com.teoware.refapp.dao.dto.InsertAuthorRequest;
import com.teoware.refapp.dao.dto.InsertAuthorResponse;
import com.teoware.refapp.dao.dto.SelectAuthorPasswordRequest;
import com.teoware.refapp.dao.dto.SelectAuthorPasswordResponse;
import com.teoware.refapp.dao.dto.SelectAuthorRequest;
import com.teoware.refapp.dao.dto.SelectAuthorResponse;
import com.teoware.refapp.dao.dto.UpdateAuthorRequest;
import com.teoware.refapp.dao.dto.UpdateAuthorResponse;
import com.teoware.refapp.dao.mock.AuthorDaoMock;
import com.teoware.refapp.dao.sql.SqlStatement;
import com.teoware.refapp.dao.test.TestDataFactory;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.util.PasswordHandler;

@Category(com.teoware.refapp.test.SystemTestGroup.class)
public class AuthorDaoSysTest {

	private static AuthorDaoMock authorDao;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		authorDao = new AuthorDaoMock();
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
	public void testInsertAuthorJohn() {
		try {
			int rowsAffected = insertAuthorJohn();

			assertEquals(4, rowsAffected);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectAuthorJohn() {
		try {
			List<Author> authorList = selectAuthor("john.doe");

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
			int rowsAffected = insertAuthorJane();
			assertEquals(4, rowsAffected);

			List<Author> authorList = selectAuthor("jane.doe");

			assertNotNull(authorList);
			assertEquals(1, authorList.size());
			Author author = authorList.get(0);

			assertInsertAuthorJane(author);

			author.getAuthorId().setStatus(AuthorStatus.ACTIVE);
			author.getAuthorInfo().setEmail("jane.doe@epost.net");
			author.getAuthorInfo().setPhone("+47 22334455");
			author.getAuthorAddress().setAddress("Nygata 2");
			author.getAuthorAddress().setPostalCode("1122");

			rowsAffected = updateAuthor(author, null);
			assertEquals(3, rowsAffected);

			authorList = selectAuthor("jane.doe");

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
			int rowsAffected = insertAuthorJonah();
			assertEquals(4, rowsAffected);

			List<AuthorPassword> authorPasswordList = selectAuthorPassword("jonah.doe");

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
	public void testSelectAllAuthorsShouldBeThree() {
		try {
			List<Author> authorList = selectAllAuthors();

			assertNotNull(authorList);
			assertEquals(3, authorList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDeleteAuthorJohn() {
		try {
			int rowsAffected = deleteAuthor("john.doe");

			assertEquals(1, rowsAffected);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectAllAuthorsShouldBeTwo() {
		try {
			List<Author> authorList = selectAllAuthors();

			assertNotNull(authorList);
			assertEquals(2, authorList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int insertAuthor(Author author, AuthorPassword authorPassword) throws DaoException {
		InsertAuthorRequest request = new InsertAuthorRequest(author, authorPassword);
		InsertAuthorResponse response = authorDao.insertAuthor(request);
		return response.getRowsAffected();
	}

	private int updateAuthor(Author author, AuthorPassword authorPassword) throws DaoException {
		UpdateAuthorRequest request = new UpdateAuthorRequest(author, authorPassword);
		UpdateAuthorResponse response = authorDao.updateAuthor(request);
		return response.getRowsAffected();
	}

	private List<Author> selectAuthor(String userName) throws DaoException {
		SelectAuthorRequest request = new SelectAuthorRequest(userName);
		SelectAuthorResponse response = authorDao.selectAuthor(request);
		return response.getAuthorList();
	}

	private List<AuthorPassword> selectAuthorPassword(String userName) throws DaoException {
		SelectAuthorPasswordRequest request = new SelectAuthorPasswordRequest(userName);
		SelectAuthorPasswordResponse response = authorDao.selectAuthorPassword(request);
		return response.getAuthorPasswordList();
	}

	private List<Author> selectAllAuthors() throws DaoException {
		SelectAuthorResponse response = authorDao.selectAllAuthors();
		return response.getAuthorList();
	}

	private int deleteAuthor(String userName) throws DaoException {
		DeleteAuthorRequest request = new DeleteAuthorRequest(userName);
		DeleteAuthorResponse response = authorDao.deleteAuthor(request);
		return response.getRowsAffected();
	}

	private int insertAuthorJohn() throws DaoException {
		Author author = TestDataFactory.createAuthorJohn();
		AuthorPassword authorPassword = TestDataFactory.createAuthorJohnPassword();
		return insertAuthor(author, authorPassword);
	}

	private int insertAuthorJane() throws DaoException {
		Author author = TestDataFactory.createAuthorJane();
		AuthorPassword authorPassword = TestDataFactory.createAuthorJanePassword();
		return insertAuthor(author, authorPassword);
	}

	private int insertAuthorJonah() throws DaoException {
		Author author = TestDataFactory.createAuthorJonah();
		AuthorPassword authorPassword = TestDataFactory.createAuthorJonahPassword();
		return insertAuthor(author, authorPassword);
	}

	private static void assertInsertAuthorJohn(Author author) {
		assertNotNull(author.getAuthorId());
		assertEquals("john.doe", author.getAuthorId().getUserName());
		assertEquals(AuthorStatus.PENDING, author.getAuthorId().getStatus());
		assertTrue(author.getAuthorId().getCreated().before(author.getAuthorId().getModified())
				|| author.getAuthorId().getCreated().equals(author.getAuthorId().getModified()));

		assertNotNull(author.getAuthorInfo());
		assertEquals("John", author.getAuthorInfo().getFirstName());
		assertEquals("Doe", author.getAuthorInfo().getLastName());
		assertNotNull(author.getAuthorInfo().getBirthDate());
		assertEquals(Gender.MALE, author.getAuthorInfo().getGender());
		assertEquals("john.doe@email.com", author.getAuthorInfo().getEmail());
		assertEquals("+47 23456789", author.getAuthorInfo().getPhone());

		assertNotNull(author.getAuthorAddress());
		assertEquals("Storgata 1", author.getAuthorAddress().getAddress());
		assertEquals("1234", author.getAuthorAddress().getPostalCode());
		assertEquals("Oslo", author.getAuthorAddress().getMunicipality());
		assertEquals("Oslo", author.getAuthorAddress().getRegion());
		assertEquals("Norway", author.getAuthorAddress().getCountry());
	}

	private static void assertInsertAuthorJane(Author author) {
		assertNotNull(author.getAuthorId());
		assertTrue("jane.doe".equals(author.getAuthorId().getUserName()));
		assertTrue(AuthorStatus.PENDING.equals(author.getAuthorId().getStatus()));
		assertTrue(author.getAuthorId().getCreated().before(author.getAuthorId().getModified())
				|| author.getAuthorId().getCreated().equals(author.getAuthorId().getModified()));

		assertNotNull(author.getAuthorInfo());
		assertEquals("Jane", author.getAuthorInfo().getFirstName());
		assertEquals("Doe", author.getAuthorInfo().getLastName());
		assertNotNull(author.getAuthorInfo().getBirthDate());
		assertEquals(Gender.FEMALE, author.getAuthorInfo().getGender());
		assertEquals("jane.doe@email.com", author.getAuthorInfo().getEmail());
		assertEquals("+47 98765432", author.getAuthorInfo().getPhone());

		assertNotNull(author.getAuthorAddress());
		assertEquals("Lillegata 1", author.getAuthorAddress().getAddress());
		assertEquals("1010", author.getAuthorAddress().getPostalCode());
		assertEquals("Oslo", author.getAuthorAddress().getMunicipality());
		assertEquals("Oslo", author.getAuthorAddress().getRegion());
		assertEquals("Norway", author.getAuthorAddress().getCountry());
	}

	private static void assertUpdateAuthorJane(Author author) {
		assertNotNull(author.getAuthorId());
		assertEquals("jane.doe", author.getAuthorId().getUserName());
		assertEquals(AuthorStatus.ACTIVE, author.getAuthorId().getStatus());
		assertTrue(author.getAuthorId().getCreated().before(author.getAuthorId().getModified())
				|| author.getAuthorId().getCreated().equals(author.getAuthorId().getModified()));

		assertNotNull(author.getAuthorInfo());
		assertEquals("Jane", author.getAuthorInfo().getFirstName());
		assertEquals("Doe", author.getAuthorInfo().getLastName());
		assertNotNull(author.getAuthorInfo().getBirthDate());
		assertEquals(Gender.FEMALE, author.getAuthorInfo().getGender());
		assertEquals("jane.doe@epost.net", author.getAuthorInfo().getEmail());
		assertEquals("+47 22334455", author.getAuthorInfo().getPhone());

		assertNotNull(author.getAuthorAddress());
		assertEquals("Nygata 2", author.getAuthorAddress().getAddress());
		assertEquals("1122", author.getAuthorAddress().getPostalCode());
		assertEquals("Oslo", author.getAuthorAddress().getMunicipality());
		assertEquals("Oslo", author.getAuthorAddress().getRegion());
		assertEquals("Norway", author.getAuthorAddress().getCountry());
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
