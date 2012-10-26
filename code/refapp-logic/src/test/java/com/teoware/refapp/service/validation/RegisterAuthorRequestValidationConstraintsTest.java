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

import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.util.ServiceBeanFactory;
import com.teoware.refapp.service.util.TestHelper;

public class RegisterAuthorRequestValidationConstraintsTest {

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
	public void testRegisterAuthorRequestHeaderAndBodyNull() {
		RegisterAuthorRequest request = new RegisterAuthorRequest(null, null, null);
		
		Set<ConstraintViolation<RegisterAuthorRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(2, constraintViolations.size());
	}

	@Test
	public void testRegisterAuthorRequestHeaderAndBodyNotNull() {
		RegisterAuthorRequest request = TestHelper.populateRegisterAuthorRequest();
		
		Set<ConstraintViolation<RegisterAuthorRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testRegisterAuthorRequestAuthorFieldsNull() {
		RegisterAuthorRequest request = ServiceBeanFactory.createRegisterAuthorRequestBean();
		request.getBody().setAuthorId(null);
		request.getBody().setAuthorInfo(null);
		request.getBody().setAuthorAddress(null);
		
		Set<ConstraintViolation<RegisterAuthorRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(3, constraintViolations.size());
	}

	@Test
	public void testRegisterAuthorRequestAuthorIdUserNameNull() {
		RegisterAuthorRequest request = TestHelper.populateRegisterAuthorRequest();
		request.getBody().getAuthorId().setUserName(null);
		
		Set<ConstraintViolation<RegisterAuthorRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterAuthorRequestAuthorIdUserNameExactlyToShortString() {
		RegisterAuthorRequest request = TestHelper.populateRegisterAuthorRequest();
		request.getBody().getAuthorId().setUserName("ab");
		
		Set<ConstraintViolation<RegisterAuthorRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterAuthorRequestAuthorIdUserNameExactlyLongEnoughString() {
		RegisterAuthorRequest request = TestHelper.populateRegisterAuthorRequest();
		request.getBody().getAuthorId().setUserName("abc");
		
		Set<ConstraintViolation<RegisterAuthorRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testRegisterAuthorRequestAuthorIdUserNameExactlyTooLongString() {
		RegisterAuthorRequest request = TestHelper.populateRegisterAuthorRequest();
		request.getBody().getAuthorId().setUserName("abcdefghijklmnopqrstu");
		
		Set<ConstraintViolation<RegisterAuthorRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterAuthorRequestAuthorIdUserNameExactlyMaxLengthString() {
		RegisterAuthorRequest request = TestHelper.populateRegisterAuthorRequest();
		request.getBody().getAuthorId().setUserName("abcdefghijklmnopqrst");
		
		Set<ConstraintViolation<RegisterAuthorRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(0, constraintViolations.size());
	}

	@Test
	public void testRegisterAuthorRequestAuthorIdUserNameContainSpaces() {
		RegisterAuthorRequest request = TestHelper.populateRegisterAuthorRequest();
		request.getBody().getAuthorId().setUserName("abc def");
		
		Set<ConstraintViolation<RegisterAuthorRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(1, constraintViolations.size());
	}

	@Test
	public void testRegisterAuthorRequestAuthorIdUserNameInvalidCharacter() {
		RegisterAuthorRequest request = TestHelper.populateRegisterAuthorRequest();
		request.getBody().getAuthorId().setUserName("abc#def");
		
		Set<ConstraintViolation<RegisterAuthorRequest>> constraintViolations = validator.validate(request);
		
		assertEquals(1, constraintViolations.size());
	}
}
