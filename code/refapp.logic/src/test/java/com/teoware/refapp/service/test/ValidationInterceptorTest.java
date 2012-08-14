package com.teoware.refapp.service.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.mock.ValidationInterceptorMock;
import com.teoware.refapp.service.util.ServiceBeanFactory;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.service.validation.group.RegisterAuthorRequestGroup;

public class ValidationInterceptorTest {

	private ValidationInterceptorMock validationInterceptor;

	@BeforeClass
	public static void oneTimeSetUp() {
	}

	@AfterClass
	public static void oneTimeTearDown() {
	}

	@Before
	public void setUp() throws Exception {
		validationInterceptor = new ValidationInterceptorMock();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=ValidationException.class)
	public void testValidateRegisterAuthorRequestThrowsValidationException() throws ValidationException, ServiceException {
		RegisterAuthorRequest request = ServiceBeanFactory.createRegisterAuthorRequestBean();
		request.getBody().setAuthorId(null);
		List<? super Object> params = new ArrayList<Object>();
		params.add(request);
		validationInterceptor.validateParams(params, RegisterAuthorRequestGroup.class);
	}

	public void testValidateRegisterAuthorRequestHasOneConstraintViolation() throws ServiceException {
		RegisterAuthorRequest request = ServiceBeanFactory.createRegisterAuthorRequestBean();
		request.getBody().setAuthorId(null);
		List<? super Object> params = new ArrayList<Object>();
		params.add(request);
		try {
			validationInterceptor.validateParams(params, RegisterAuthorRequestGroup.class);
		} catch (ValidationException e) {
			assertEquals(1, e.getConstraintViolations().size());
		}
	}
}
