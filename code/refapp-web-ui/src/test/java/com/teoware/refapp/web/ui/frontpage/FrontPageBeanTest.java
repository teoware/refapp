package com.teoware.refapp.web.ui.frontpage;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
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
