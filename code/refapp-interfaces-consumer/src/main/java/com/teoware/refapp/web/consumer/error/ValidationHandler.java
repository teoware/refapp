package com.teoware.refapp.web.consumer.error;

import static java.lang.System.out;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.service.validation.ValidationException;

public class ValidationHandler {

	private static final Logger LOG = LoggerFactory.getLogger(ValidationHandler.class);

	public static void handle(ValidationException e) {
		LOG.debug("Validation error occured");
		out.println("Validation error occured");
		throw new RuntimeException(e);
	}
}
