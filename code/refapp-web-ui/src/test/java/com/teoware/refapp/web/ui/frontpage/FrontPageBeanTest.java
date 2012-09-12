package com.teoware.refapp.web.ui.frontpage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FrontPageBeanTest {

	private FrontPageBean frontPage;
	
	@Before
	public void setUp() {
		frontPage = new FrontPageBean();
	}
	
	@Test
	public void testFrontPage() {
		frontPage.setName("Ego");
		assertEquals("Ego", frontPage.getName());
	}
}
