package com.teoware.refapp.dao.itest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.dao.DaoException;
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
import com.teoware.refapp.dao.test.util.TestDataFactory;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.util.PasswordHandler;

public class AuthorDaoTest {

	private AuthorDaoMock authorDao;

	@BeforeClass
	public static void oneTimeSetUp() {
		
	}

	@AfterClass
	public static void oneTimeTearDown() {
	}

	@Before
	public void setUp() throws Exception {
		authorDao = new AuthorDaoMock();
		deleteAuthor("john.doe");
		deleteAuthor("jane.doe");
		deleteAuthor("jonah.doe");
	}

	@After
	public void tearDown() throws Exception {
		deleteAuthor("john.doe");
		deleteAuthor("jane.doe");
		deleteAuthor("jonah.doe");
		authorDao.closeAll();
	}

	@Test
	public void testInsertAndSelectAuthor() {
		try {
			Author john = TestDataFactory.createAuthorJohn();
			AuthorPassword johnsPassword = TestDataFactory.createAuthorJohnPassword();
			
			int rowsAffected = insertAuthor(john, johnsPassword);
			assertTrue(rowsAffected == 4);
			
			List<Author> authorList = selectAuthor("john.doe");
			
			assertNotNull(authorList);
			assertTrue(authorList.size() == 1);
			Author author = authorList.get(0);
			
			assertNotNull(author.getAuthorId());
			assertEquals("john.doe", author.getAuthorId().getUserName());
			assertEquals(AuthorStatus.PENDING, author.getAuthorId().getStatus());
			assertTrue(author.getAuthorId().getCreated().before(author.getAuthorId().getModified()) ||
					author.getAuthorId().getCreated().equals(author.getAuthorId().getModified()));
			
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertUpdateAndSelectAuthor() {
		try {
			
			Author jane = TestDataFactory.createAuthorJane();
			AuthorPassword janesPassword = TestDataFactory.createAuthorJanePassword();
			
			int rowsAffected = insertAuthor(jane, janesPassword);
			assertTrue(rowsAffected == 4);
			
			List<Author> authorList = selectAuthor("jane.doe");
			
			assertNotNull(authorList);
			assertTrue(authorList.size() == 1);
			Author author = authorList.get(0);
			
			assertNotNull(author.getAuthorId());
			assertTrue("jane.doe".equals(author.getAuthorId().getUserName()));
			assertTrue(AuthorStatus.PENDING.equals(author.getAuthorId().getStatus()));
			assertTrue(author.getAuthorId().getCreated().before(author.getAuthorId().getModified()) ||
					author.getAuthorId().getCreated().equals(author.getAuthorId().getModified()));
			
			assertNotNull(author.getAuthorInfo());
			assertTrue("Jane".equals(author.getAuthorInfo().getFirstName()));
			assertTrue("Doe".equals(author.getAuthorInfo().getLastName()));
			assertNotNull(author.getAuthorInfo().getBirthDate());
			assertTrue(Gender.FEMALE.equals(author.getAuthorInfo().getGender()));
			assertTrue("jane.doe@email.com".equals(author.getAuthorInfo().getEmail()));
			assertTrue("+47 98765432".equals(author.getAuthorInfo().getPhone()));
			
			assertNotNull(author.getAuthorAddress());
			assertTrue("Lillegata 1".equals(author.getAuthorAddress().getAddress()));
			assertTrue("1010".equals(author.getAuthorAddress().getPostalCode()));
			assertTrue("Oslo".equals(author.getAuthorAddress().getMunicipality()));
			assertTrue("Oslo".equals(author.getAuthorAddress().getRegion()));
			assertTrue("Norway".equals(author.getAuthorAddress().getCountry()));
			
			jane.getAuthorId().setStatus(AuthorStatus.ACTIVE);
			jane.getAuthorInfo().setEmail("jane.doe@epost.net");
			jane.getAuthorInfo().setPhone("+47 22334455");
			jane.getAuthorAddress().setAddress("Nygata 2");
			jane.getAuthorAddress().setPostalCode("1122");
			
			janesPassword = null;
			
			rowsAffected = updateAuthor(jane, janesPassword);
			assertTrue(rowsAffected == 3);
			
			authorList = selectAuthor("jane.doe");
			
			assertNotNull(authorList);
			assertTrue(authorList.size() == 1);
			author = authorList.get(0);
			
			assertNotNull(author.getAuthorId());
			assertTrue("jane.doe".equals(author.getAuthorId().getUserName()));
			assertTrue(AuthorStatus.ACTIVE.equals(author.getAuthorId().getStatus()));
			assertTrue(author.getAuthorId().getCreated().before(author.getAuthorId().getModified()) ||
					author.getAuthorId().getCreated().equals(author.getAuthorId().getModified()));
			
			assertNotNull(author.getAuthorInfo());
			assertTrue("Jane".equals(author.getAuthorInfo().getFirstName()));
			assertTrue("Doe".equals(author.getAuthorInfo().getLastName()));
			assertNotNull(author.getAuthorInfo().getBirthDate());
			assertTrue(Gender.FEMALE.equals(author.getAuthorInfo().getGender()));
			assertTrue("jane.doe@epost.net".equals(author.getAuthorInfo().getEmail()));
			assertTrue("+47 22334455".equals(author.getAuthorInfo().getPhone()));
			
			assertNotNull(author.getAuthorAddress());
			assertTrue("Nygata 2".equals(author.getAuthorAddress().getAddress()));
			assertTrue("1122".equals(author.getAuthorAddress().getPostalCode()));
			assertTrue("Oslo".equals(author.getAuthorAddress().getMunicipality()));
			assertTrue("Oslo".equals(author.getAuthorAddress().getRegion()));
			assertTrue("Norway".equals(author.getAuthorAddress().getCountry()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertAndSelectAuthorPassword() {
		try {
			Author jonah = TestDataFactory.createAuthorJonah();
			AuthorPassword jonahsPassword = TestDataFactory.createAuthorJonahPassword();			
			
			int rowsAffected = insertAuthor(jonah, jonahsPassword);
			assertTrue(rowsAffected == 4 || rowsAffected == 5);
			
			List<AuthorPassword> authorPasswordList = selectAuthorPassword("jonah.doe");
			
			assertNotNull(authorPasswordList);
			assertTrue(authorPasswordList.size() == 1);
			AuthorPassword authorPassword = authorPasswordList.get(0);
			
			assertNotNull(authorPassword.getPassword());
			assertTrue(PasswordHandler.verifyPassword("jonahsPassword", authorPassword.getPassword()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int insertAuthor(Author author, AuthorPassword authorPassword) throws DaoException {
		InsertAuthorRequest request = new InsertAuthorRequest(author, authorPassword);
		InsertAuthorResponse response  = authorDao.insertAuthor(request);
		return response.getRowsAffected();
	}

	private int updateAuthor(Author author, AuthorPassword authorPassword) throws DaoException {
		UpdateAuthorRequest request = new UpdateAuthorRequest(author, authorPassword);
		UpdateAuthorResponse response  = authorDao.updateAuthor(request);
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

	private int deleteAuthor(String userName) throws DaoException {
		DeleteAuthorRequest request = new DeleteAuthorRequest(userName);
		DeleteAuthorResponse response = authorDao.deleteAuthor(request);
		return response.getRowsAffected();
	}
}
