package com.teoware.refapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.UserDao;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.CreateUserPasswordInput;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class UserServiceTest {

	@InjectMocks
	private UserService userService = new UserServiceBean();

	@Mock
	private UserDao userDao;

	@Mock
	private RegisterUserRequest registerUserRequest;

	@Mock
	private CreateUserOutput createUserOutput;

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void testRegisterUserResponseNotNull() throws DaoException, ServiceException {
		when(userDao.createUser(any(CreateUserInput.class))).thenReturn(createUserOutput);

		RegisterUserResponse registerUserResponse = userService.registerUser(registerUserRequest);

		verify(userDao).createUser(any(CreateUserInput.class));
		verify(userDao).createUserPassword(any(CreateUserPasswordInput.class));

		assertNotNull(registerUserResponse);
		assertNotNull(registerUserResponse.getBody());
		assertNotNull(registerUserResponse.getBody().getResult());
		assertEquals(Result.SUCCESS, registerUserResponse.getBody().getResult());
	}

	@Test(expected = ServiceException.class)
	public void testRegisterUserThrowsServiceException() throws DaoException, ServiceException {
		when(userDao.createUser(any(CreateUserInput.class))).thenThrow(new DaoException());

		userService.registerUser(registerUserRequest);
	}
}
