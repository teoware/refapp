package com.teoware.refapp.web.consumer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;
import com.teoware.refapp.service.facade.UserServiceFacade;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.web.consumer.util.TestDataFactory;
import com.teoware.refapp.web.consumer.vo.RegisterUserRequestVO;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class UserServiceConsumerTest {

	@InjectMocks
	private UserServiceConsumer userServiceConsumer = new UserServiceConsumerBean();

	@Mock
	private UserServiceFacade userServiceFacade;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void testThatRegisterUserIsSuccessful() throws ParseException, ValidationException, ServiceException {
		User john = TestDataFactory.createUserJohn();
		UserPassword johnPassword = TestDataFactory.createUserJohnPassword();
		RegisterUserRequestVO vo = new RegisterUserRequestVO(john, johnPassword);

		when(userServiceFacade.registerUser(any(RegisterUserRequest.class))).thenReturn(
				new RegisterUserResponse(null, null));

		userServiceConsumer.registerUser(vo);

		verify(userServiceFacade).registerUser(any(RegisterUserRequest.class));
		verifyNoMoreInteractions(userServiceFacade);
	}
}
