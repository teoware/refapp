package com.teoware.refapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.service.impl.AuthorServiceImpl;

public class AuthorServiceTest {
	
	@BeforeClass
	public static void oneTimeSetUp() {
	}

	@AfterClass
	public static void oneTimeTearDown() {
	}
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private AuthorService createAuthorService() {
		return new AuthorServiceImpl();
	}
	
	@Test
	public void testGetName() {
		AuthorService authorService = createAuthorService();
		assertNotNull(authorService);
		assertEquals("Thomas", authorService.getName());
	}

}
