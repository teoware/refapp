package com.teoware.refapp.dao;

import java.sql.Connection;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AuthorDaoTest {

	@InjectMocks
	private Connection connection;
	
	@Mock
	private AuthorDao authorDao;
	
	@BeforeClass
	public static void oneTimeSetUp() {
	}

	@AfterClass
	public static void oneTimeTearDown() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}
}
