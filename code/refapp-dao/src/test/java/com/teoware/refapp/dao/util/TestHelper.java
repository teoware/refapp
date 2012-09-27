package com.teoware.refapp.dao.util;

import org.apache.commons.validator.routines.EmailValidator;

public class TestHelper {

	public static boolean isCorrectlyFormattedEmail(String email) {
		return EmailValidator.getInstance().isValid(email);
	}
}
