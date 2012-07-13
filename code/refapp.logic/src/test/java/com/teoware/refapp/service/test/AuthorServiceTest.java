package com.teoware.refapp.service.test;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.dao.AuthorDaoLocal;
import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.message.InsertAuthorRequest;
import com.teoware.refapp.model.common.OperationResult;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.ValidationException;
import com.teoware.refapp.service.message.RegisterAuthorRequest;
import com.teoware.refapp.service.message.RegisterAuthorResponse;
import com.teoware.refapp.service.mock.AuthorServiceMock;
import com.teoware.refapp.service.test.util.TestHelper;

public class AuthorServiceTest {

	private AuthorServiceMock authorService;
	private AuthorDaoLocal authorDaoMock;

	@BeforeClass
	public static void oneTimeSetUp() {
	}

	@AfterClass
	public static void oneTimeTearDown() {
	}

	@Before
	public void setUp() throws Exception {
		authorService = new AuthorServiceMock();
		authorDaoMock = createMock(AuthorDaoLocal.class);
		authorService.setAuthorDao(authorDaoMock);
	}

	@After
	public void tearDown() throws Exception {
	}

	private RegisterAuthorRequest createValidRegisterAuthorRequest() {
		RegisterAuthorRequest request = TestHelper.populateRegisterAuthorRequest();
		return request;
	}

	@Test
	public void testAuthorServiceNotNull() {
		assertNotNull(authorService);
	}

	@Test
	public void testCreateRegisterAuthorRequest() {
		RegisterAuthorRequest request = createValidRegisterAuthorRequest();
		assertNotNull(request);
	}

	@Test
	public void testRegisterAuthorResponseNotNull() throws ValidationException, ServiceException {
		RegisterAuthorRequest request = createValidRegisterAuthorRequest();
		RegisterAuthorResponse response = authorService.registerAuthor(request);
		assertNotNull(response);
	}

	@Test
	public void testRegisterAuthorResponseHeaderNotNull() throws ValidationException, ServiceException {
		RegisterAuthorRequest request = createValidRegisterAuthorRequest();
		RegisterAuthorResponse response = authorService.registerAuthor(request);
		assertNotNull(response.getHeader());
	}

	@Test
	public void testRegisterAuthorResponseHeaderMessageIdNotNull() throws ValidationException, ServiceException {
		RegisterAuthorRequest request = createValidRegisterAuthorRequest();
		RegisterAuthorResponse response = authorService.registerAuthor(request);
		assertNotNull(response.getHeader().getMessageId());
		assertNotNull(response.getHeader().getMessageId().getUuid());
	}

	@Test
	public void testRegisterAuthorResponseHeaderTimestampNotNull() throws ValidationException, ServiceException {
		RegisterAuthorRequest request = createValidRegisterAuthorRequest();
		RegisterAuthorResponse response = authorService.registerAuthor(request);
		assertNotNull(response.getHeader().getTimestamp());
		assertNotNull(response.getHeader().getTimestamp().getTimestamp());
	}

	@Test
	public void testRegisterAuthorResponseBodyNotNull() throws ValidationException, ServiceException {
		RegisterAuthorRequest request = createValidRegisterAuthorRequest();
		RegisterAuthorResponse response = authorService.registerAuthor(request);
		assertNotNull(response.getBody());
	}

	@Test
	public void testRegisterAuthorResponseBodyIsCorrectType() throws ValidationException, ServiceException {
		RegisterAuthorRequest request = createValidRegisterAuthorRequest();
		RegisterAuthorResponse response = authorService.registerAuthor(request);
		assertTrue(response.getBody() instanceof OperationResult);
	}

	@Test
	public void testRegisterAuthorResponseBodyHasSuccessResult() throws ValidationException, ServiceException {
		RegisterAuthorRequest request = createValidRegisterAuthorRequest();
		RegisterAuthorResponse response = authorService.registerAuthor(request);
		assertTrue(Result.SUCCESS.equals(response.getBody().getResult()));
		assertTrue(response.getBody().getDescription() == null);
	}

	@Test(expected=ValidationException.class)
	public void testRegisterAuthorThrowsValidationException() throws ValidationException, ServiceException {
		RegisterAuthorRequest request = createValidRegisterAuthorRequest();
		request.getBody().setAuthorId(null);
		authorService.registerAuthor(request);
	}

	@Test(expected=ServiceException.class)
	public void testRegisterAuthorThrowsServiceException() throws Exception {
		expect(authorDaoMock.insertAuthor(isA(InsertAuthorRequest.class))).andThrow(new DaoException());
		replay(authorDaoMock);
		RegisterAuthorRequest request = createValidRegisterAuthorRequest();
		authorService.registerAuthor(request);
		verify(authorDaoMock);
	}
}
