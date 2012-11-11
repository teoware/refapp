package com.teoware.refapp.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class AuthorServiceIT {

	//private InitialContext context;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		/*Properties properties = new Properties();
		properties.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
		context = new InitialContext(properties);*/
	}

	@After
	public void tearDown() throws Exception {
	}
/*
	private AuthorService createAuthorService() throws NamingException {
		Object object = context.lookup("AuthorServiceImplLocal");
		assertNotNull(object);
		assertTrue(object instanceof AuthorServiceLocal);
		return (AuthorServiceLocal) object;
	}*/
}
