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
		request.getBody().setUserId(null);
		request.getBody().setUserInfo(null);
		request.getBody().setUserAddress(null);
		
		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(3, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUserIdUserNameNull() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUserId().setUserName(null);
		
		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUserIdUserNameExactlyToShortString() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUserId().setUserName("ab");
		
		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUserIdUserNameExactlyLongEnoughString() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUserId().setUserName("abc");
		
		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUserIdUserNameExactlyTooLongString() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUserId().setUserName("abcdefghijklmnopqrstu");
		
		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUserIdUserNameExactlyMaxLengthString() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUserId().setUserName("abcdefghijklmnopqrst");
		
		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUserIdUserNameContainSpaces() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUserId().setUserName("abc def");
		
		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterUserRequestUserIdUserNameInvalidCharacter() {
		RegisterUserRequest request = TestHelper.populateRegisterUserRequest();
		request.getBody().getUserId().setUserName("abc#def");
		
		Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(1, constraintViolations.size());
	}
}