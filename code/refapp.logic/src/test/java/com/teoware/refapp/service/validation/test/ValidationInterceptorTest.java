package com.teoware.refapp.service.validation.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.interceptor.ValidationInterceptor;
import com.teoware.refapp.service.message.RegisterAuthorRequest;
import com.teoware.refapp.service.mock.AuthorServiceMock;
import com.teoware.refapp.service.util.BeanFactory;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.service.validation.group.RegisterAuthorRequestGroup;
import com.teoware.refapp.service.validation.mock.ValidationInterceptorMock;
import com.teoware.refapp.service.validation.util.ValidationUtils;

public class ValidationInterceptorTest {

	private ValidationInterceptor validationInterceptor;

	@BeforeClass
	public static void oneTimeSetUp() {
	}

	@AfterClass
	public static void oneTimeTearDown() {
	}

	@Before
	public void setUp() throws Exception {
		validationInterceptor = new ValidationInterceptor();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidateRegisterAuthorRequestThrowsValidationException() throws ValidationException, ServiceException {
		RegisterAuthorRequest request = BeanFactory.createRegisterAuthorRequestBean();
		request.getBody().setAuthorId(null);
		List<? super Object> list = new ArrayList();
		list.add(request);
		new ValidationInterceptorMock().validateParams(list, RegisterAuthorRequestGroup.class);
	}

}
