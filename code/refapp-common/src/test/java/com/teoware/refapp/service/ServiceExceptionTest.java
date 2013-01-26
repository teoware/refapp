package com.teoware.refapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ServiceExceptionTest {

	@Test
	public void testThrowServiceException() {
		try {
			throw new ServiceException();
		} catch (ServiceException e) {
			assertNull(e.getMessage());
			assertNull(e.getCause());
		}
	}

	@Test
	public void testThrowServiceExceptionWithMessage() {
		try {
			throw new ServiceException("whatever");
		} catch (ServiceException e) {
			assertNotNull(e.getMessage());
			assertEquals("whatever", e.getMessage());
			assertNull(e.getCause());
		}
	}

	@Test
	public void testThrowServiceExceptionWithCause() {
		try {
			throw new ServiceException(new RuntimeException());
		} catch (ServiceException e) {
			assertNotNull(e.getMessage());
			assertEquals("java.lang.RuntimeException", e.getMessage());
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof RuntimeException);
		}
	}

	@Test
	public void testThrowServiceExceptionWithMessageAndCause() {
		try {
			throw new ServiceException("whatever", new RuntimeException());
		} catch (ServiceException e) {
			assertNotNull(e.getMessage());
			assertEquals("whatever", e.getMessage());
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof RuntimeException);
		}
	}
}
