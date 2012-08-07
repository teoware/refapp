package com.teoware.refapp.util;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordGenerator {

	public static String encryptPassword(String password) {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		return passwordEncryptor.encryptPassword(password);
	}
}
