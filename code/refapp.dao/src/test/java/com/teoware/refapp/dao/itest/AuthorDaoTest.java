package com.teoware.refapp.dao.itest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.message.DeleteAuthorRequest;
import com.teoware.refapp.dao.message.DeleteAuthorResponse;
import com.teoware.refapp.dao.message.InsertAuthorRequest;
import com.teoware.refapp.dao.message.InsertAuthorResponse;
import com.teoware.refapp.dao.message.SelectAuthorPasswordRequest;
import com.teoware.refapp.dao.message.SelectAuthorPasswordResponse;
import com.teoware.refapp.dao.message.SelectAuthorRequest;
import com.teoware.refapp.dao.message.SelectAuthorResponse;
import com.teoware.refapp.dao.message.UpdateAuthorRequest;
import com.teoware.refapp.dao.message.UpdateAuthorResponse;
import com.teoware.refapp.dao.mock.AuthorDaoMock;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.util.BeanFactory;
import com.teoware.refapp.util.DateUtils;
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
		//deleteAuthor("john.doe");
		//deleteAuthor("jane.doe");
		//deleteAuthor("jonah.doe");
		authorDao.closeAll();
	}

	/*@Test
	public void testInsert() throws ParseException, DaoException {
		Author john = createAuthorJohn();
		AuthorPassword johnsPassword = createAuthorJohnPassword();			
		insertAuthor(john, johnsPassword);
		Author jane = createAuthorJane();
		AuthorPassword janesPassword = createAuthorJanePassword();			
		insertAuthor(jane, janesPassword);
		Author jonah = createAuthorJonah();
		AuthorPassword jonahsPassword = createAuthorJonahPassword();			
		insertAuthor(jonah, jonahsPassword);
	}*/

	@Test
	public void testInsertAndSelectAuthor() {
		try {
			Author john = createAuthorJohn();
			AuthorPassword johnsPassword = createAuthorJohnPassword();
			
			int rowsAffected = insertAuthor(john, johnsPassword);
			assertTrue(rowsAffected == 4);
			
			List<Author> authorList = selectAuthor("john.doe");
			
			assertNotNull(authorList);
			assertTrue(authorList.size() == 1);
			Author author = authorList.get(0);
			
			assertNotNull(author.getAuthorId());
			assertTrue("john.doe".equals(author.getAuthorId().getUserName()));
			assertTrue(AuthorStatus.PENDING.equals(author.getAuthorId().getStatus()));
			assertTrue(author.getAuthorId().getCreated().before(author.getAuthorId().getModified()) ||
					author.getAuthorId().getCreated().equals(author.getAuthorId().getModified()));
			
			assertNotNull(author.getAuthorInfo());
			assertTrue("John".equals(author.getAuthorInfo().getFirstName()));
			assertTrue("Doe".equals(author.getAuthorInfo().getLastName()));
			assertNotNull(author.getAuthorInfo().getBirthDate());
			assertTrue(Gender.MALE.equals(author.getAuthorInfo().getGender()));
			assertTrue("john.doe@email.com".equals(author.getAuthorInfo().getEmail()));
			assertTrue("+47 23456789".equals(author.getAuthorInfo().getPhone()));
			
			assertNotNull(author.getAuthorAddress());
			assertTrue("Storgata 1".equals(author.getAuthorAddress().getAddress()));
			assertTrue("1234".equals(author.getAuthorAddress().getPostalCode()));
			assertTrue("Oslo".equals(author.getAuthorAddress().getMunicipality()));
			assertTrue("Oslo".equals(author.getAuthorAddress().getRegion()));
			assertTrue("Norway".equals(author.getAuthorAddress().getCountry()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertUpdateAndSelectAuthor() {
		try {
			
			Author jane = createAuthorJane();
			AuthorPassword janesPassword = createAuthorJanePassword();
			
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
			Author jonah = createAuthorJonah();
			AuthorPassword jonahsPassword = createAuthorJonahPassword();			
			
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

	private Author createAuthorJohn() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Author author = BeanFactory.createAuthorBean();
		author.getAuthorId().setUserName("john.doe");
		author.getAuthorId().setCreated(calendar);
		author.getAuthorId().setModified(calendar);
		author.getAuthorInfo().setFirstName("John");
		author.getAuthorInfo().setLastName("Doe");
		author.getAuthorInfo().setBirthDate(DateUtils.stringToDate("1975-01-01"));
		author.getAuthorInfo().setGender(Gender.MALE);
		author.getAuthorInfo().setEmail("john.doe@email.com");
		author.getAuthorInfo().setPhone("+47 23456789");
		author.getAuthorAddress().setAddress("Storgata 1");
		author.getAuthorAddress().setPostalCode("1234");
		author.getAuthorAddress().setMunicipality("Oslo");
		author.getAuthorAddress().setRegion("Oslo");
		author.getAuthorAddress().setCountry("Norway");
		return author;
	}

	private AuthorPassword createAuthorJohnPassword() {
		AuthorPassword authorPassword = new AuthorPassword();
		authorPassword.setPassword(PasswordHandler.encryptPassword("johnsPassword"));
		return authorPassword;
	}

	private Author createAuthorJane() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Author author = BeanFactory.createAuthorBean();
		author.getAuthorId().setUserName("jane.doe");
		author.getAuthorId().setCreated(calendar);
		author.getAuthorId().setModified(calendar);
		author.getAuthorInfo().setFirstName("Jane");
		author.getAuthorInfo().setLastName("Doe");
		author.getAuthorInfo().setBirthDate(DateUtils.stringToDate("1970-12-30"));
		author.getAuthorInfo().setGender(Gender.FEMALE);
		author.getAuthorInfo().setEmail("jane.doe@email.com");
		author.getAuthorInfo().setPhone("+47 98765432");
		author.getAuthorAddress().setAddress("Lillegata 1");
		author.getAuthorAddress().setPostalCode("1010");
		author.getAuthorAddress().setMunicipality("Oslo");
		author.getAuthorAddress().setRegion("Oslo");
		author.getAuthorAddress().setCountry("Norway");
		return author;
	}

	private AuthorPassword createAuthorJanePassword() {
		AuthorPassword authorPassword = new AuthorPassword();
		authorPassword.setPassword(PasswordHandler.encryptPassword("janesPassword"));
		return authorPassword;
	}

	private Author createAuthorJonah() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Author author = BeanFactory.createAuthorBean();
		author.getAuthorId().setUserName("jonah.doe");
		author.getAuthorId().setCreated(calendar);
		author.getAuthorId().setModified(calendar);
		author.getAuthorInfo().setFirstName("Jonah");
		author.getAuthorInfo().setLastName("Doe");
		author.getAuthorInfo().setBirthDate(DateUtils.stringToDate("1975-01-01"));
		author.getAuthorInfo().setGender(Gender.MALE);
		author.getAuthorInfo().setEmail("jonah.doe@email.com");
		author.getAuthorInfo().setPhone("+47 19283746");
		author.getAuthorAddress().setAddress("Mellomgata 1");
		author.getAuthorAddress().setPostalCode("1221");
		author.getAuthorAddress().setMunicipality("Oslo");
		author.getAuthorAddress().setRegion("Oslo");
		author.getAuthorAddress().setCountry("Norway");
		return author;
	}

	private AuthorPassword createAuthorJonahPassword() {
		AuthorPassword authorPassword = new AuthorPassword();
		authorPassword.setPassword(PasswordHandler.encryptPassword("jonahsPassword"));
		return authorPassword;
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
