package com.teoware.refapp.web.ui.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.beans.IntrospectionException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserAddress;
import com.teoware.refapp.model.user.UserInfo;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.testtools.JavaBeanTester;
import com.teoware.refapp.web.consumer.UserServiceConsumer;
import com.teoware.refapp.web.consumer.vo.RegisterUserRequestVO;

public class RegisterUserControllerBeanTest {

	@InjectMocks
	RegisterUserControllerBean controller;

	@Mock
	private UserServiceConsumer consumer;

	@Mock
	private RegisterUserRequestVO vo;

	@Mock
	private User user;

	@Mock
	private UserInfo userInfo;

	@Mock
	private UserAddress userAddress;

	@Mock
	private UserPassword userPassword;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		when(vo.getUser()).thenReturn(user);
		when(user.getUserInfo()).thenReturn(userInfo);
		when(user.getUserAddress()).thenReturn(userAddress);
		when(vo.getUserPassword()).thenReturn(userPassword);
	}

	@Test
	public void testGettersAndSetters() throws IntrospectionException {
		JavaBeanTester.test(RegisterUserControllerBean.class);
	}

	@Test
	public void testTitle() {
		assertNotNull(controller.getTitle());
		assertTrue(controller.getTitle().startsWith("RefApp"));
	}

	@Test
	public void testOnClickRegisterButtonCreatesUser() {
		when(userPassword.getPassword()).thenReturn("whatever");
		controller.setConfirmPassword("whatever");

		controller.onClickRegisterButton();

		verify(consumer).registerUser(any(RegisterUserRequestVO.class));
	}
}
