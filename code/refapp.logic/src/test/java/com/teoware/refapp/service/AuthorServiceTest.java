package com.teoware.refapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthorServiceTest {

	private InitialContext context;
	
	@BeforeClass
	public static void oneTimeSetUp() {
	}

	@AfterClass
	public static void oneTimeTearDown() {
	}
	
	@Before
	public void setUp() throws Exception {
		Properties properties = new Properties();
		properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
		context = new InitialContext(properties);
	}

	@After
	public void tearDown() throws Exception {
	}

	private AuthorServiceLocal createAuthorService() throws NamingException {
		Object object = context.lookup("AuthorServiceImplLocal");
		assertNotNull(object);
		assertTrue(object instanceof AuthorServiceLocal);
		return (AuthorServiceLocal) object;
	}
	
	@Test
	public void testGetName() throws NamingException {
		AuthorServiceLocal authorService = createAuthorService();
		assertEquals("Thomas", authorService.getName());
	}

}
