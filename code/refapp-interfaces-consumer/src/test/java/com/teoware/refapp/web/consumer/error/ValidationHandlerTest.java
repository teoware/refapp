package com.teoware.refapp.web.consumer.error;

import org.junit.Test;

import com.teoware.refapp.service.validation.ValidationException;

public class ValidationHandlerTest {

	@SuppressWarnings("static-access")
	@Test(expected = RuntimeException.class)
	public void testHandleValidationException() {
		new ValidationHandler().handle(new ValidationException(null));
	}
}
