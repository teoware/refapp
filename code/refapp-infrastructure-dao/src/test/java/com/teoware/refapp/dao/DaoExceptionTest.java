package com.teoware.refapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DaoExceptionTest {

	@Test
	public void testThrowWithDefaultConstructor() {
		try {
			throw new DaoException();
		} catch (DaoException e) {
			assertNull(e.getMessage());
			assertNull(e.getCause());
		}
	}

	@Test
	public void testThrowWithMessageConstructor() {
		try {
			throw new DaoException("whatever");
		} catch (DaoException e) {
			assertNotNull(e.getMessage());
			assertEquals("whatever", e.getMessage());
			assertNull(e.getCause());
		}
	}

	@Test
	public void testThrowWithNullMessageConstructor() {
		try {
			throw new DaoException((String) null);
		} catch (DaoException e) {
			assertNull(e.getMessage());
			assertNull(e.getCause());
		}
	}

	@Test
	public void testThrowWithCauseConstructor() {
		try {
			throw new DaoException(new RuntimeException());
		} catch (DaoException e) {
			assertNotNull(e.getMessage());
			assertEquals(RuntimeException.class.getName(), e.getMessage());
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof RuntimeException);
		}
	}

	@Test
	public void testThrowWithNullCauseConstructor() {
		try {
			throw new DaoException((Throwable) null);
		} catch (DaoException e) {
			assertNull(e.getMessage());
			assertNull(e.getCause());
		}
	}

	@Test
	public void testThrowWithMessageAndCauseConstructor() {
		try {
			throw new DaoException("whatever", new RuntimeException());
		} catch (DaoException e) {
			assertNotNull(e.getMessage());
			assertEquals("whatever", e.getMessage());
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof RuntimeException);
		}
	}

	@Test
	public void testThrowWithNullMessageAndNullCauseConstructor() {
		try {
			throw new DaoException(null, null);
		} catch (DaoException e) {
			assertNull(e.getMessage());
			assertNull(e.getCause());
		}
	}

	@Test
	public void testThrowWithCauseConstructorWithMessageInException() {
		try {
			throw new DaoException(new RuntimeException("whatever"));
		} catch (DaoException e) {
			assertNotNull(e.getMessage());
			assertEquals(RuntimeException.class.getName() + ": whatever", e.getMessage());
			assertNotNull(e.getCause());
			assertTrue(e.getCause() instanceof RuntimeException);
		}
	}
}
