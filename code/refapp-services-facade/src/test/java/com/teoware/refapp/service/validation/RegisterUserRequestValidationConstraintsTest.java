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
import com.teoware.refapp.service.util.ServiceBeanFactory;
import com.teoware.refapp.service.test.TestDataFactory;

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
    public void testRegisterUserRequestHeaderBodyAndPasswordNull() {
        RegisterUserRequest request = new RegisterUserRequest(null, null, null);

        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        assertEquals(3, constraintViolations.size());
    }

    @Test
    public void testRegisterUserRequestComplete() {
        RegisterUserRequest request = TestDataFactory.createRegisterUserRequestJohn();

        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testRegisterUserRequestUserFieldsNull() {
        RegisterUserRequest request = ServiceBeanFactory.createRegisterUserRequestBean();
        request.getBody().setUsername(null);
        request.getBody().setUserDetails(null);
        request.getBody().setUserAddress(null);
        request.getBody().setUserStatus(null);

        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        assertEquals(5, constraintViolations.size());
    }

    @Test
    public void testRegisterUserRequestUsernameUserNameNull() {
        RegisterUserRequest request = TestDataFactory.createRegisterUserRequestJohn();
        request.getBody().getUsername().setUsername(null);

        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testRegisterUserRequestUsernameUsernameExactlyToShortString() {
        RegisterUserRequest request = TestDataFactory.createRegisterUserRequestJohn();
        request.getBody().getUsername().setUsername("ab");

        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testRegisterUserRequestUsernameUsernameExactlyLongEnoughString() {
        RegisterUserRequest request = TestDataFactory.createRegisterUserRequestJohn();
        request.getBody().getUsername().setUsername("abc");

        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testRegisterUserRequestUsernameUsernameExactlyTooLongString() {
        RegisterUserRequest request = TestDataFactory.createRegisterUserRequestJohn();
        request.getBody().getUsername().setUsername("abcdefghijklmnopqrstu");

        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testRegisterUserRequestUsernameUsernameExactlyMaxLengthString() {
        RegisterUserRequest request = TestDataFactory.createRegisterUserRequestJohn();
        request.getBody().getUsername().setUsername("abcdefghijklmnopqrst");

        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        assertEquals(0, constraintViolations.size());
    }

    @Test
    public void testRegisterUserRequestUsernameUsernameContainSpaces() {
        RegisterUserRequest request = TestDataFactory.createRegisterUserRequestJohn();
        request.getBody().getUsername().setUsername("abc def");

        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        assertEquals(1, constraintViolations.size());
    }

    @Test
    public void testRegisterUserRequestUsernameUsernameInvalidCharacter() {
        RegisterUserRequest request = TestDataFactory.createRegisterUserRequestJohn();
        request.getBody().getUsername().setUsername("abc#def");

        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        assertEquals(1, constraintViolations.size());
    }
}
