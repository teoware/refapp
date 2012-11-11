package com.teoware.refapp.service.validation;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.NotNull;

import org.junit.Test;

import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.validation.group.ValidationGroup;
import com.teoware.refapp.service.validation.util.ValidationUtils;

public class ValidationUtilsTest {

	@Test(expected = ServiceException.class)
	public void testValidateWithNullGroup() throws ValidationException, ServiceException {
		ValidationUtils.validate(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateWithNullParam() throws ValidationException, ServiceException {
		ValidationUtils.validate(null, ValidationGroup.class);
	}

	@Test
	public void testValidateWithObjectParam() throws ValidationException, ServiceException {
		ValidationUtils.validate(new Object(), ValidationGroup.class);
	}

	@Test(expected = ValidationException.class)
	public void testValidateWithDummyParamShallThrowValidationException() throws ValidationException, ServiceException {
		ValidationUtils.validate(new DummyParam(), ValidationGroup.class);
	}

	@Test
	public void testValidateWithDummyParamNotNullConstraintViolations() throws ServiceException {
		Set<? extends ConstraintViolation<?>> violations = null;

		try {
			ValidationUtils.validate(new DummyParam(), ValidationGroup.class);
		} catch (ValidationException e) {
			violations = e.getConstraintViolations();
		}

		assertNotNull(violations);
	}

	@Test
	public void testValidateWithDummyParamNotEmptyConstraintViolations() throws ServiceException {
		Set<? extends ConstraintViolation<?>> violations = null;

		try {
			ValidationUtils.validate(new DummyParam(), ValidationGroup.class);
		} catch (ValidationException e) {
			violations = e.getConstraintViolations();
		}

		assertFalse(violations.isEmpty());
	}

	@Test
	public void testValidateWithDummyParamHasOneConstraintViolation() throws ServiceException {
		Set<? extends ConstraintViolation<?>> violations = null;

		try {
			ValidationUtils.validate(new DummyParam(), ValidationGroup.class);
		} catch (ValidationException e) {
			violations = e.getConstraintViolations();
		}

		assertEquals(1, violations.size());
	}

	@Test
	public void testValidateWithDummyParamConstraintViolationIsCorrectRootBean() throws ServiceException {
		Set<? extends ConstraintViolation<?>> violations = null;

		try {
			ValidationUtils.validate(new DummyParam(), ValidationGroup.class);
		} catch (ValidationException e) {
			violations = e.getConstraintViolations();
		}

		for (ConstraintViolation<?> violation : violations) {
			assertTrue(violation.getRootBean() instanceof DummyParam);
		}
	}

	@Test
	public void testValidateWithDummyParamConstraintViolationTargetCorrectField() throws ServiceException {
		Set<? extends ConstraintViolation<?>> violations = null;

		try {
			ValidationUtils.validate(new DummyParam(), ValidationGroup.class);
		} catch (ValidationException e) {
			violations = e.getConstraintViolations();
		}

		for (ConstraintViolation<?> violation : violations) {
			assertEquals("field", violation.getPropertyPath().toString());
		}
	}

	@Test
	public void testValidateWithDummyParamConstraintViolationHasCorrectInvalidValue() throws ServiceException {
		Set<? extends ConstraintViolation<?>> violations = null;

		try {
			ValidationUtils.validate(new DummyParam(), ValidationGroup.class);
		} catch (ValidationException e) {
			violations = e.getConstraintViolations();
		}

		for (ConstraintViolation<?> violation : violations) {
			assertEquals(null, violation.getInvalidValue());
		}
	}

	public class DummyParam {

		@NotNull
		private String field;

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}
	}
}
