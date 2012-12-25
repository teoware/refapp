package com.teoware.refapp.web.consumer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.FindUserRequest;
import com.teoware.refapp.service.dto.FindUserResponse;
import com.teoware.refapp.service.dto.ListUsersResponse;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;
import com.teoware.refapp.service.facade.UserServiceFacade;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.web.consumer.vo.FindUserRequestVO;
import com.teoware.refapp.web.consumer.vo.RegisterUserRequestVO;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class UserServiceConsumerTest {

	@InjectMocks
	private UserServiceConsumerBean serviceConsumer;

	@Mock
	private UserServiceFacade serviceFacade;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testRegisterUserIsSuccessful() throws ParseException, ValidationException, ServiceException {
		when(serviceFacade.registerUser(any(RegisterUserRequest.class))).thenReturn(mock(RegisterUserResponse.class));

		serviceConsumer.registerUser(mock(RegisterUserRequestVO.class));

		verify(serviceFacade).registerUser(any(RegisterUserRequest.class));
	}

	@Test(expected = RuntimeException.class)
	public void testRegisterUserThrowsValidationException() throws ValidationException, ServiceException {
		when(serviceFacade.registerUser(any(RegisterUserRequest.class))).thenThrow(mock(ValidationException.class));

		serviceConsumer.registerUser(mock(RegisterUserRequestVO.class));
	}

	@Test(expected = RuntimeException.class)
	public void testRegisterUserThrowsServiceException() throws ValidationException, ServiceException {
		when(serviceFacade.registerUser(any(RegisterUserRequest.class))).thenThrow(mock(ServiceException.class));

		serviceConsumer.registerUser(mock(RegisterUserRequestVO.class));
	}

	@Test
	public void testFindUserIsSuccessful() throws ParseException, ValidationException, ServiceException {
		when(serviceFacade.findUser(any(FindUserRequest.class))).thenReturn(mock(FindUserResponse.class));

		serviceConsumer.findUser(mock(FindUserRequestVO.class));

		verify(serviceFacade).findUser(any(FindUserRequest.class));
	}

	@Test(expected = RuntimeException.class)
	public void testFindUserThrowsValidationException() throws ValidationException, ServiceException {
		when(serviceFacade.findUser(any(FindUserRequest.class))).thenThrow(mock(ValidationException.class));

		serviceConsumer.findUser(mock(FindUserRequestVO.class));
	}

	@Test(expected = RuntimeException.class)
	public void testFindUserThrowsServiceException() throws ValidationException, ServiceException {
		when(serviceFacade.findUser(any(FindUserRequest.class))).thenThrow(mock(ServiceException.class));

		serviceConsumer.findUser(mock(FindUserRequestVO.class));
	}

	@Test
	public void testListUsersIsSuccessful() throws ParseException, ValidationException, ServiceException {
		when(serviceFacade.listUsers()).thenReturn(mock(ListUsersResponse.class));

		serviceConsumer.listUsers();

		verify(serviceFacade).listUsers();
	}

	@Test(expected = RuntimeException.class)
	public void testListUsersThrowsServiceException() throws ValidationException, ServiceException {
		when(serviceFacade.listUsers()).thenThrow(mock(ServiceException.class));

		serviceConsumer.listUsers();
	}
}
