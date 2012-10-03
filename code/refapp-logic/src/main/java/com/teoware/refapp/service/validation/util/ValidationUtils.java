package com.teoware.refapp.service.validation.util;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.validation.InternalValidationException;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.service.validation.group.ValidationGroup;

public class ValidationUtils {

	private static Validator validator;

	public static final String NOT_NULL_ERR_MSG = "1";
	public static final String TOO_BIG_ERR_MSG = "2";
	public static final String TOO_SMALL_ERR_MSG = "3";

	static {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	public static void validate(Object param, Class<? extends ValidationGroup> group) throws ValidationException,
			ServiceException {
		final Set<ConstraintViolation<?>> result = new HashSet<ConstraintViolation<?>>();

		if (group == null) {
			throw new ServiceException("Validation group can not be null");
		}

		try {
			result.addAll(validator.validate(param, group));
		} catch (InternalValidationException e) {
			Exception ex = (Exception) e.getCause();
			ex.printStackTrace(); // additional logging of error

			if (ex instanceof DaoException) {
				throw new ServiceException("DAO error", ex);
			} else if (ex instanceof ServiceException) {
				throw (ServiceException) ex;
			} else if (ex instanceof RuntimeException) {
				throw (RuntimeException) ex;
			} else { // Exception
				throw new ServiceException(ex);
			}
		}

		if (!result.isEmpty()) {
			throw new ValidationException(result);
		}
	}
}
