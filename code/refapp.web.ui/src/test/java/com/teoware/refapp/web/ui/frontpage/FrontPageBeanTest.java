package com.teoware.refapp.web.ui.frontpage;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class FrontPageBeanTest {

	private FrontPageBean frontPage;
	
	@Before
	public void setup() {
		frontPage = new FrontPageBean();
	}
	
	@Test
	public void testFrontPage() {
		frontPage.setName("Ego");
		Assert.assertEquals("Ego", frontPage.getName());
	}
}
