package com.teoware.refapp.web.consumer.error;

import com.teoware.refapp.service.validation.ValidationException;

public class ValidationHandler {

	public static void handle(ValidationException e) {
		throw new RuntimeException(e);
	}
}
