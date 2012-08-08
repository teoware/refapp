package com.teoware.refapp.util;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordHandler {

	public static String encryptPassword(String plainPassword) {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		return passwordEncryptor.encryptPassword(plainPassword);
	}

	public static boolean verifyPassword(String plainPassword, String encryptedPassword) {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
	}
}
