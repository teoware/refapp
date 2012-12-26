package com.teoware.refapp.web.ui.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.teoware.refapp.web.ui.controller.AbstractControllerBean;

public class AbstractControllerBeanTest {

	private AbstractControllerBean controller;

	@Before
	public void setUp() throws Exception {
		controller = new AbstractControllerBean() {
		};
	}

	@Test
	public void testTitle() {
		assertNotNull(controller.getTitle());
		assertTrue(controller.getTitle().startsWith("RefApp"));
	}

	@Test
	public void testGetStackTrace() {
		String e = controller.getStackTrace(new Exception());

		assertNotNull(e);
	}
}
