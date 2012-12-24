package com.teoware.refapp.web.ui.user;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.beans.IntrospectionException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.test.JavaBeanTester;
import com.teoware.refapp.web.consumer.UserServiceConsumer;
import com.teoware.refapp.web.consumer.vo.RegisterUserRequestVO;

public class RegisterUserControllerBeanTest {

	@InjectMocks
	RegisterUserControllerBean controller;

	@Mock
	private UserServiceConsumer consumer;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
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
		controller.onClickRegisterButton();

		verify(consumer).registerUser(any(RegisterUserRequestVO.class));
	}
}
