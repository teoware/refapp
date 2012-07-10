package com.teoware.refapp.consumer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.consumer.impl.AuthorServiceConsumerImpl;

public class AuthorServiceConsumerTest {
	
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
	public void testServiceInjection() {
		assertNotNull(AuthorServiceConsumerImpl.getInstance());
		assertNull(AuthorServiceConsumerImpl.getInstance().listAuthors());
	}

}
