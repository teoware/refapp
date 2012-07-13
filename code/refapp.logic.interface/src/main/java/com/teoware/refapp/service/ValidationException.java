package com.teoware.refapp.service;

import javax.ejb.ApplicationException;

@ApplicationException( rollback = false )
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
}
