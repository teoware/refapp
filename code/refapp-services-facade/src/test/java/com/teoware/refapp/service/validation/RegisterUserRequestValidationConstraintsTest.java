package com.teoware.refapp.service.validation;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.service.dto.RegisterUserRequest;
import com.teoware.refapp.service.testtools.TestHelper;
import com.teoware.refapp.service.util.ServiceBeanFactory;

public class RegisterUserRequestValidationConstraintsTest {

	private static Validator validator;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
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
	public void testRegisterUserRequestHeaderAndBodyNull() {
		RegisterUserRequest request = new RegisterUserRequest(null, null, null);

		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

		assertEquals(2, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestHeaderAndBodyNotNull() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();

		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUserFieldsNull() {
		RegisterUserRequest request = ServiceBeanFactory.createRegisterUserRequestBean();
		request.getBody().setUsername(null);
		request.getBody().setUserInfo(null);
		request.getBody().setUserAddress(null);
		request.getBody().setUserStatus(null);

		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

		assertEquals(3, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUsernameUserNameNull() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUsername().setUsername(null);

		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUsernameUsernameExactlyToShortString() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUsername().setUsername("ab");

		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUsernameUsernameExactlyLongEnoughString() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUsername().setUsername("abc");

		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUsernameUsernameExactlyTooLongString() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUsername().setUsername("abcdefghijklmnopqrstu");

		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUsernameUsernameExactlyMaxLengthString() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUsername().setUsername("abcdefghijklmnopqrst");

		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUsernameUsernameContainSpaces() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUsername().setUsername("abc def");

		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUsernameUsernameInvalidCharacter() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUsername().setUsername("abc#def");

		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

		assertEquals(1, constraintViolations.size());
	}
}
