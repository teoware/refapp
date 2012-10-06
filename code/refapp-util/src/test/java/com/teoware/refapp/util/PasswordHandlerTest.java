package com.teoware.refapp.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.teoware.refapp.util.PasswordHandler;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class PasswordHandlerTest {

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

	@Test
	public void testEncryptedPasswords() {
		String firstPlainPassword = "firstTestPassword";
		String secondPlainPassword = "secondTestPassword";
		String firstEncryptedPassword = PasswordHandler.encryptPassword(firstPlainPassword);
		String secondEncryptedPassword = PasswordHandler.encryptPassword(secondPlainPassword);
		boolean firstCorrectPasswordCheck = PasswordHandler.verifyPassword(firstPlainPassword, firstEncryptedPassword);
		boolean secondCorrectPasswordCheck = PasswordHandler.verifyPassword(secondPlainPassword, secondEncryptedPassword);
		boolean firstBadPasswordCheck = PasswordHandler.verifyPassword(firstPlainPassword, secondEncryptedPassword);
		boolean secondBadPasswordCheck = PasswordHandler.verifyPassword(secondPlainPassword, firstEncryptedPassword);
		
		assertFalse(firstPlainPassword.equals(firstEncryptedPassword));
		assertFalse(secondPlainPassword.equals(secondEncryptedPassword));
		assertFalse(firstEncryptedPassword.equals(secondEncryptedPassword));
		
		assertTrue(firstCorrectPasswordCheck);
		assertTrue(secondCorrectPasswordCheck);
		
		assertFalse(firstBadPasswordCheck);
		assertFalse(secondBadPasswordCheck);
	}
}
