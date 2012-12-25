package com.teoware.refapp.web.consumer.error;

import org.junit.Test;

import com.teoware.refapp.service.ServiceException;

public class ErrorHandlerTest {

	@SuppressWarnings("static-access")
	@Test(expected = RuntimeException.class)
	public void testHandleServiceException() {
		new ErrorHandler().handle(new ServiceException());
	}
}
