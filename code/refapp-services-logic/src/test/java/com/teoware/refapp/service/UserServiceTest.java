package com.teoware.refapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
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
import com.teoware.refapp.dao.dto.CreateUserAddressInput;
import com.teoware.refapp.dao.dto.CreateUserAddressOutput;
import com.teoware.refapp.dao.dto.CreateUserInfoInput;
import com.teoware.refapp.dao.dto.CreateUserInfoOutput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.dto.CreateUserPasswordInput;
import com.teoware.refapp.dao.dto.CreateUserPasswordOutput;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class UserServiceTest {

	@InjectMocks
	private UserServiceBean userService;

	@Mock
	private RegisterUserRequest registerUserRequest;

	@Mock
	private User user;

	@Mock
	private UserDao userDao;

	@Mock
	private CreateUserOutput createUserOutput;

	@Mock
	private CreateUserInfoOutput createUserInfoOutput;

	@Mock
	private CreateUserAddressOutput createUserAddressOutput;

	@Mock
	private CreateUserPasswordOutput createUserPasswordOutput;

	@Before
	public void setUp() {
		initMocks(this);
		when(registerUserRequest.getBody()).thenReturn(user);
	}

	@Test
	public void testRegisterUserResponseNotNull() {
		try {
			when(userDao.createUser(any(CreateUserInput.class))).thenReturn(createUserOutput);
			when(userDao.createUserInfo(any(CreateUserInfoInput.class))).thenReturn(createUserInfoOutput);
			when(userDao.createUserAddress(any(CreateUserAddressInput.class))).thenReturn(createUserAddressOutput);
			when(userDao.createUserPassword(any(CreateUserPasswordInput.class))).thenReturn(createUserPasswordOutput);

			RegisterUserResponse registerUserResponse = userService.registerUser(registerUserRequest);

			verify(userDao).createUser(any(CreateUserInput.class));
			verify(userDao).createUserPassword(any(CreateUserPasswordInput.class));

			assertNotNull(registerUserResponse);
			assertNotNull(registerUserResponse.getBody());
			assertNotNull(registerUserResponse.getBody().getResult());
			assertEquals(Result.SUCCESS, registerUserResponse.getBody().getResult());
		} catch (Throwable t) {
			t.printStackTrace();
			fail(t.getMessage());
		}
	}

	@Test(expected = ServiceException.class)
	public void testRegisterUserThrowsServiceException() throws DaoException, ServiceException {
		when(userDao.createUser(any(CreateUserInput.class))).thenThrow(new DaoException());

		userService.registerUser(registerUserRequest);
	}
}
