package com.teoware.refapp.service.validation;

import java.util.Set;

import javax.ejb.ApplicationException;
import javax.validation.ConstraintViolation;

@ApplicationException( rollback = false )
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Set<? extends ConstraintViolation<?>> constraintViolations;

	public ValidationException( Set<? extends ConstraintViolation<?>> constraintViolations ) {
		this.constraintViolations = constraintViolations;
	}

	public Set<? extends ConstraintViolation<?>> getConstraintViolations() {
		return this.constraintViolations;
	}
}
