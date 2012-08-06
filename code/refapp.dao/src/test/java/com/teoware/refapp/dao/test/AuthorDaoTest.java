package com.teoware.refapp.dao.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.dao.AuthorDaoLocal;
import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.message.SelectAuthorResponse;
import com.teoware.refapp.dao.mock.AuthorDaoMock;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.enums.Gender;

public class AuthorDaoTest {

	AuthorDaoLocal authorDao;

	@BeforeClass
	public static void oneTimeSetUp() {
	}

	@AfterClass
	public static void oneTimeTearDown() {
	}

	@Before
	public void setUp() throws Exception {
		authorDao = new AuthorDaoMock();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelectAuthor() throws DaoException {
		SelectAuthorResponse response = authorDao.selectAuthor();
		assertNotNull(response);
		assertNotNull(response.getAuthorList());
		assertTrue(response.getAuthorList().size() > 0);
		Author author = response.getAuthorList().get(0);
		
		assertNotNull(author.getAuthorId());
		assertTrue(author.getAuthorId().getUserName().length() > 0);
		
		assertNotNull(author.getAuthorInfo());
		assertTrue(author.getAuthorInfo().getFirstName().length() > 0 && author.getAuthorInfo().getLastName().length() > 0);
		assertNotNull(author.getAuthorInfo().getBirthDate());
		assertNotNull(author.getAuthorInfo().getGender());
		assertTrue(author.getAuthorInfo().getGender().equals(Gender.MALE));
	}
}
