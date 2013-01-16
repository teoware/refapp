package com.teoware.refapp.service.itest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.dto.RegisterUserResponse;
import com.teoware.refapp.service.itest.mock.UserServiceMock;
import com.teoware.refapp.test.util.TestDataFactory;

public class UserServiceIT {

	private static UserServiceMock service;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		service = new UserServiceMock();
	}

	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		service.terminateConnection();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterUser() throws ServiceException {
		RegisterUserRequest request = TestDataFactory.createRegisterUserRequestJohn();
		RegisterUserResponse response = service.registerUser(request);

		assertEquals(Result.SUCCESS, response.getBody().getResult());
		System.out.println(response.getBody().getDescription());
	}
}
