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
import com.teoware.refapp.dao.message.InsertAuthorRequest;
import com.teoware.refapp.dao.message.InsertAuthorResponse;
import com.teoware.refapp.dao.message.PurgeAuthorRequest;
import com.teoware.refapp.dao.message.PurgeAuthorResponse;
import com.teoware.refapp.dao.message.SelectAuthorRequest;
import com.teoware.refapp.dao.message.SelectAuthorResponse;
import com.teoware.refapp.dao.mock.AuthorDaoMock;
import com.teoware.refapp.dao.test.util.TestHelper;
import com.teoware.refapp.dao.util.DateUtils;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.model.enums.Gender;
import com.teoware.refapp.model.util.BeanFactory;

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
		purgeAuthor("john.doe");
		purgeAuthor("jane.doe");
	}

	@After
	public void tearDown() throws Exception {
		purgeAuthor("john.doe");
		purgeAuthor("jane.doe");
		authorDao.closeAll();
	}

	@Test
	public void testInsertAuthor() {
		try {
			
			int rowsAffected = insertAuthor(createAuthorJohn());
			assertTrue(rowsAffected == 3);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSelectAuthor() {
		try {
			
			int rowsAffected = insertAuthor(createAuthorJane());
			assertTrue(rowsAffected == 3);
			
			List<Author> authorList = selectAuthor("jane.doe");
			
			assertNotNull(authorList);
			assertTrue(authorList.size() == 1);
			Author author = authorList.get(0);
			
			assertNotNull(author.getAuthorId());
			assertNotNull(author.getAuthorId().getUserName());
			assertTrue(author.getAuthorId().getUserName().length() > 0);
			assertNotNull(author.getAuthorId().getStatus());
			assertTrue(author.getAuthorId().getStatus().equals(AuthorStatus.PENDING));
			assertNotNull(author.getAuthorId().getCreated());
			assertNotNull(author.getAuthorId().getModified());
			assertTrue(author.getAuthorId().getCreated().before(author.getAuthorId().getModified()) ||
					author.getAuthorId().getCreated().equals(author.getAuthorId().getModified()));
			
			assertNotNull(author.getAuthorInfo());
			assertTrue(author.getAuthorInfo().getFirstName().length() > 0 &&
					author.getAuthorInfo().getLastName().length() > 0);
			assertNotNull(author.getAuthorInfo().getBirthDate());
			assertNotNull(author.getAuthorInfo().getGender());
			assertTrue(author.getAuthorInfo().getGender().equals(Gender.FEMALE));
			assertNotNull(author.getAuthorInfo().getEmail());
			assertTrue(TestHelper.isCorrectlyFormattedEmail(author.getAuthorInfo().getEmail()));
			assertNotNull(author.getAuthorInfo().getPhone());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Author createAuthorJohn() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Author author = BeanFactory.createAuthorBean();
		author.getAuthorId().setUserName("john.doe");
		author.getAuthorId().setStatus(AuthorStatus.ACTIVE);
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

	private Author createAuthorJane() throws ParseException {
		Calendar calendar = Calendar.getInstance();
		Author author = BeanFactory.createAuthorBean();
		author.getAuthorId().setUserName("jane.doe");
		author.getAuthorId().setStatus(AuthorStatus.ACTIVE);
		author.getAuthorId().setCreated(calendar);
		author.getAuthorId().setModified(calendar);
		author.getAuthorInfo().setFirstName("Jane");
		author.getAuthorInfo().setLastName("Doe");
		author.getAuthorInfo().setBirthDate(DateUtils.stringToDate("1975-01-01"));
		author.getAuthorInfo().setGender(Gender.FEMALE);
		author.getAuthorInfo().setEmail("jane.doe@email.com");
		author.getAuthorInfo().setPhone("+47 98765432");
		author.getAuthorAddress().setAddress("Storgata 1");
		author.getAuthorAddress().setPostalCode("1234");
		author.getAuthorAddress().setMunicipality("Oslo");
		author.getAuthorAddress().setRegion("Oslo");
		author.getAuthorAddress().setCountry("Norway");
		return author;
	}

	private int insertAuthor(Author author) throws DaoException {
		InsertAuthorRequest request = new InsertAuthorRequest(author);
		InsertAuthorResponse response  = authorDao.insertAuthor(request);
		return response.getRowsAffected();
	}

	@SuppressWarnings("unused")
	private List<Author> selectAuthor() throws DaoException {
		SelectAuthorResponse response = authorDao.selectAuthor();
		return response.getAuthorList();
	}

	private List<Author> selectAuthor(String userName) throws DaoException {
		SelectAuthorRequest request = new SelectAuthorRequest(userName);
		SelectAuthorResponse response = authorDao.selectAuthor(request);
		return response.getAuthorList();
	}

	private int purgeAuthor(String userName) throws DaoException {
		PurgeAuthorRequest request = new PurgeAuthorRequest(userName);
		PurgeAuthorResponse response = authorDao.purgeAuthor(request);
		return response.getRowsAffected();
	}
}
