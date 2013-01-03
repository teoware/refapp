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

import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.UserDao;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.model.common.OperationResult;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;
import com.teoware.refapp.service.testtools.TestHelper;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class UserServiceRegisterUserTest {

	@InjectMocks
	private UserService userService = new UserServiceBean();

	@Mock
	private UserDao userDao;

	private RegisterUserRequest request;
	private RegisterUserResponse response;

	@Before
	public void setUp() throws Exception {
		initMocks(this);

		request = TestHelper.populateRegisterUserRequest();
		response = userService.registerUser(request);
	}

	@Test
	public void testRegisterUserResponseNotNull() throws DaoException {
		verify(userDao).createUser(Mockito.any(CreateUserInput.class));

		assertNotNull(response);
	}

	@Test
	public void testRegisterUserResponseHeaderNotNull() throws DaoException {
		verify(userDao).createUser(Mockito.any(CreateUserInput.class));

		assertNotNull(response.getHeader());
	}

	@Test
	public void testRegisterUserResponseHeaderMessageIdNotNull() throws DaoException {
		verify(userDao).createUser(Mockito.any(CreateUserInput.class));

		assertNotNull(response.getHeader().getMessageId());
		assertNotNull(response.getHeader().getMessageId().getUuid());
	}

	@Test
	public void testRegisterUserResponseHeaderTimestampNotNull() throws DaoException {
		verify(userDao).createUser(Mockito.any(CreateUserInput.class));

		assertNotNull(response.getHeader().getTimestamp());
		assertNotNull(response.getHeader().getTimestamp().getTimestamp());
	}

	@Test
	public void testRegisterUserResponseBodyNotNull() throws DaoException {
		verify(userDao).createUser(Mockito.any(CreateUserInput.class));

		assertNotNull(response.getBody());
	}

	@Test
	public void testRegisterUserResponseBodyIsCorrectType() throws DaoException {
		verify(userDao).createUser(Mockito.any(CreateUserInput.class));

		assertTrue(response.getBody() instanceof OperationResult);
	}

	@Test
	public void testRegisterUserResponseBodyHasSuccessResult() throws DaoException {
		verify(userDao).createUser(Mockito.any(CreateUserInput.class));

		assertEquals(Result.SUCCESS, response.getBody().getResult());
		assertTrue(response.getBody().getDescription() == null);
	}

	@Test(expected = ServiceException.class)
	public void testRegisterUserThrowsServiceException() throws DaoException, ServiceException {
		when(userDao.createUser(Mockito.any(CreateUserInput.class))).thenThrow(new DaoException());

		userService.registerUser(request);
	}
}
