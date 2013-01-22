package com.teoware.refapp.web.ui.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.web.ui.util.Globalization;

public class AbstractControllerBeanTest {

	@InjectMocks
	private AbstractControllerBean controller = new AbstractControllerBean() {
		@Override
		public String getPageTitle() {
			return "";
		}
	};

	@Mock
	protected Globalization globalization;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		when(globalization.dict(anyString())).thenReturn("RefApp");
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
