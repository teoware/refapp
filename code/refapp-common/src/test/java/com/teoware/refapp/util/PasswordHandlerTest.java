package com.teoware.refapp.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

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
	public void testEncryptedPasswords() throws UnsupportedEncodingException {
		String firstPlainPassword = "firstTestPassword";
		String firstPlainPasswordSalt = "firstTestPasswordSalt";
		String secondPlainPassword = "secondTestPassword";
		String secondPlainPasswordSalt = "secondTestPasswordSalt";
		String firstEncryptedPassword = PasswordHandler.encryptPassword(firstPlainPassword, firstPlainPasswordSalt);
		String secondEncryptedPassword = PasswordHandler.encryptPassword(secondPlainPassword, secondPlainPasswordSalt);
		boolean firstCorrectPasswordCheck = PasswordHandler.verifyPassword(firstPlainPassword, firstPlainPasswordSalt,
				firstEncryptedPassword);
		boolean secondCorrectPasswordCheck = PasswordHandler.verifyPassword(secondPlainPassword,
				secondPlainPasswordSalt, secondEncryptedPassword);
		boolean firstBadPasswordCheck = PasswordHandler.verifyPassword(firstPlainPassword, firstPlainPasswordSalt,
				secondEncryptedPassword);
		boolean secondBadPasswordCheck = PasswordHandler.verifyPassword(secondPlainPassword, secondPlainPasswordSalt,
				firstEncryptedPassword);

		assertFalse(firstPlainPassword.equals(firstEncryptedPassword));
		assertFalse(secondPlainPassword.equals(secondEncryptedPassword));
		assertFalse(firstEncryptedPassword.equals(secondEncryptedPassword));

		assertTrue(firstCorrectPasswordCheck);
		assertTrue(secondCorrectPasswordCheck);

		assertFalse(firstBadPasswordCheck);
		assertFalse(secondBadPasswordCheck);
	}

	@Test
	public void testEncrypt() throws UnsupportedEncodingException {
		String pwd = "secret password";
		String salt = PasswordHandler.generateSalt();
		
		System.out.println(PasswordHandler.encryptPassword(pwd, salt));
	}

	@Test
	public void testGenerateSalt() throws UnsupportedEncodingException {
		System.out.println(PasswordHandler.generateSalt());
	}
}
