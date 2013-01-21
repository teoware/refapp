package com.teoware.refapp.web.consumer.error;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.service.validation.ValidationException;

public class ValidationHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ValidationHandler.class);

	public static void handle(ValidationException e) {
		LOG.error("Validation error occured", e);

		Set<? extends ConstraintViolation<?>> cvSet = e.getConstraintViolations();
		for (ConstraintViolation<?> cv : cvSet) {
			LOG.error("VE: " + cv.getMessage() + " : " + cv.getRootBean().getClass().getName() + " : "
					+ cv.getPropertyPath().toString());
		}

		throw new RuntimeException(e.getMessage(), e);
	}
}
