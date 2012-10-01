package com.teoware.refapp.service.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.interceptor.InvocationContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.interceptor.ValidationInterceptor;
import com.teoware.refapp.service.util.ServiceBeanFactory;
import com.teoware.refapp.service.validation.group.RegisterAuthorRequestGroup;
import com.teoware.refapp.service.validation.util.ServiceFacade;

public class ValidationInterceptorTest {

	@InjectMocks
	private ValidationInterceptor validationInterceptor = new ValidationInterceptor();

	@Mock
	private ServiceFacade serviceFacade;
	
	@Mock
	private InvocationContext context;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidationOfNonValidatedMethod() throws Exception {
		when(context.getMethod()).thenReturn(DummyValidationClass.class.getMethod("nonValidatedMethod", (Class<?> []) null));
		
		Object object = validationInterceptor.validate(context);
		
		assertNull(object);
	}
	
	@Test(expected = ServiceException.class)
	public void testValidationOfValidatedMethodWithoutParams() throws Exception {
		when(context.getMethod()).thenReturn(DummyValidationClass.class.getMethod("validatedMethodWithoutParams", (Class<?> []) null));
		
		validationInterceptor.validate(context);
	}
	
	@Test(expected = ServiceException.class)
	public void testValidationOfValidatedMethodWithParamsButNoValidationGroup() throws Exception {
		Class<?> [] params = {String.class};
		when(context.getMethod()).thenReturn(DummyValidationClass.class.getMethod("validatedMethodWithParamsButNoValidationGroup", params));
		
		validationInterceptor.validate(context);
	}
	
	@Test
	public void testValidationOfValidatedMethodWithParamsAndValidationGroup() throws Exception {
		Class<?> [] params = {String.class};
		when(context.getMethod()).thenReturn(DummyValidationClass.class.getMethod("validatedMethodWithParamsAndValidationGroup", params));
		
		Object object = validationInterceptor.validate(context);
		
		assertNull(object);
	}
	
//	@Test(expected = ValidationException.class)
//	public void testValidateRegisterAuthorRequestThrowsValidationException() throws ValidationException,
//			ServiceException {
//		RegisterAuthorRequest request = ServiceBeanFactory.createRegisterAuthorRequestBean();
//		request.getBody().setAuthorId(null);
//		List<? super Object> params = new ArrayList<Object>();
//		params.add(request);
//		
//		Mockito.validationInterceptor.validateParams(params, RegisterAuthorRequestGroup.class);
//	}
//
//	public void testValidateRegisterAuthorRequestHasOneConstraintViolation() throws ServiceException {
//		RegisterAuthorRequest request = ServiceBeanFactory.createRegisterAuthorRequestBean();
//		request.getBody().setAuthorId(null);
//		List<? super Object> params = new ArrayList<Object>();
//		params.add(request);
//		try {
//			validationInterceptor.validateParams(params, RegisterAuthorRequestGroup.class);
//		} catch (ValidationException e) {
//			assertEquals(1, e.getConstraintViolations().size());
//		}
//	}
}
