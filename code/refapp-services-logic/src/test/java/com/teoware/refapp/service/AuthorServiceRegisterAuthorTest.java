package com.teoware.refapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.teoware.refapp.dao.AuthorDao;
import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.dto.InsertAuthorRequest;
import com.teoware.refapp.model.common.OperationResult;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.dto.RegisterAuthorResponse;
import com.teoware.refapp.service.testtools.TestHelper;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class AuthorServiceRegisterAuthorTest {

	@InjectMocks
	private AuthorService authorService = new AuthorServiceBean();

	@Mock
	private AuthorDao authorDao;

	private RegisterAuthorRequest request;
	private RegisterAuthorResponse response;

	@Before
	public void setUp() throws Exception {
		initMocks(this);

		request = TestHelper.populateRegisterAuthorRequest();
		response = authorService.registerAuthor(request);
	}

	@Test
	public void testRegisterAuthorResponseNotNull() throws DaoException {
		verify(authorDao).insertAuthor(Mockito.any(InsertAuthorRequest.class));

		assertNotNull(response);
	}

	@Test
	public void testRegisterAuthorResponseHeaderNotNull() throws DaoException {
		verify(authorDao).insertAuthor(Mockito.any(InsertAuthorRequest.class));

		assertNotNull(response.getHeader());
	}

	@Test
	public void testRegisterAuthorResponseHeaderMessageIdNotNull() throws DaoException {
		verify(authorDao).insertAuthor(Mockito.any(InsertAuthorRequest.class));

		assertNotNull(response.getHeader().getMessageId());
		assertNotNull(response.getHeader().getMessageId().getUuid());
	}

	@Test
	public void testRegisterAuthorResponseHeaderTimestampNotNull() throws DaoException {
		verify(authorDao).insertAuthor(Mockito.any(InsertAuthorRequest.class));

		assertNotNull(response.getHeader().getTimestamp());
		assertNotNull(response.getHeader().getTimestamp().getTimestamp());
	}

	@Test
	public void testRegisterAuthorResponseBodyNotNull() throws DaoException {
		verify(authorDao).insertAuthor(Mockito.any(InsertAuthorRequest.class));

		assertNotNull(response.getBody());
	}

	@Test
	public void testRegisterAuthorResponseBodyIsCorrectType() throws DaoException {
		verify(authorDao).insertAuthor(Mockito.any(InsertAuthorRequest.class));

		assertTrue(response.getBody() instanceof OperationResult);
	}

	@Test
	public void testRegisterAuthorResponseBodyHasSuccessResult() throws DaoException {
		verify(authorDao).insertAuthor(Mockito.any(InsertAuthorRequest.class));

		assertEquals(Result.SUCCESS, response.getBody().getResult());
		assertTrue(response.getBody().getDescription() == null);
	}

	@Test(expected = ServiceException.class)
	public void testRegisterAuthorThrowsServiceException() throws DaoException, ServiceException {
		when(authorDao.insertAuthor(Mockito.any(InsertAuthorRequest.class))).thenThrow(new DaoException());

		authorService.registerAuthor(request);
	}
}
