package com.teoware.refapp.web.consumer;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthorServiceConsumerITest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testThatServiceInjectionWorks() {
		assertNotNull(AuthorServiceConsumerBean.getInstance());
	}

//	@Test
//	public void testThatAuthorServiceDoesNotReturnNullForAuthorList() {
//		assertNotNull(AuthorServiceConsumerBean.getInstance().listAuthors());
//	}
}
