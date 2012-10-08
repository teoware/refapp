package com.teoware.refapp.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.teoware.refapp.dao.AuthorDao;
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
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.model.enums.Gender;

public abstract class AuthorDaoTestHelper {

	public static int insertAuthor(AuthorDao authorDao, Author author, AuthorPassword authorPassword)
			throws DaoException {
		InsertAuthorRequest request = new InsertAuthorRequest(author, authorPassword);
		InsertAuthorResponse response = authorDao.insertAuthor(request);
		return response.getRowsAffected();
	}

	public static int updateAuthor(AuthorDao authorDao, Author author, AuthorPassword authorPassword)
			throws DaoException {
		UpdateAuthorRequest request = new UpdateAuthorRequest(author, authorPassword);
		UpdateAuthorResponse response = authorDao.updateAuthor(request);
		return response.getRowsAffected();
	}

	public static List<Author> selectAuthor(AuthorDao authorDao, String userName) throws DaoException {
		SelectAuthorRequest request = new SelectAuthorRequest(userName);
		SelectAuthorResponse response = authorDao.selectAuthor(request);
		return response.getAuthorList();
	}

	public static List<AuthorPassword> selectAuthorPassword(AuthorDao authorDao, String userName) throws DaoException {
		SelectAuthorPasswordRequest request = new SelectAuthorPasswordRequest(userName);
		SelectAuthorPasswordResponse response = authorDao.selectAuthorPassword(request);
		return response.getAuthorPasswordList();
	}

	public static List<Author> selectAllAuthors(AuthorDao authorDao) throws DaoException {
		SelectAuthorResponse response = authorDao.selectAllAuthors();
		return response.getAuthorList();
	}

	public static int deleteAuthor(AuthorDao authorDao, String userName) throws DaoException {
		DeleteAuthorRequest request = new DeleteAuthorRequest(userName);
		DeleteAuthorResponse response = authorDao.deleteAuthor(request);
		return response.getRowsAffected();
	}

	public static int insertAuthorJohn(AuthorDao authorDao) throws DaoException {
		Author author = TestDataFactory.createAuthorJohn();
		AuthorPassword authorPassword = TestDataFactory.createAuthorJohnPassword();
		return insertAuthor(authorDao, author, authorPassword);
	}

	public static int insertAuthorJane(AuthorDao authorDao) throws DaoException {
		Author author = TestDataFactory.createAuthorJane();
		AuthorPassword authorPassword = TestDataFactory.createAuthorJanePassword();
		return insertAuthor(authorDao, author, authorPassword);
	}

	public static int insertAuthorJonah(AuthorDao authorDao) throws DaoException {
		Author author = TestDataFactory.createAuthorJonah();
		AuthorPassword authorPassword = TestDataFactory.createAuthorJonahPassword();
		return insertAuthor(authorDao, author, authorPassword);
	}

	public static void assertInsertAuthorJohn(Author author) {
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

	public static void assertInsertAuthorJane(Author author) {
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

	public static void assertUpdateAuthorJane(Author author) {
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
}
