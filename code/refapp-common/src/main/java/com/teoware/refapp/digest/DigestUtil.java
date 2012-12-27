package com.teoware.refapp.digest;

public final class DigestUtil {

	public static String createSalt() {
		return createSalt("");
	}

	public static String createSalt(String random) {
		return random;
	}

	public static String createDigest(String secret, String salt) {
		return secret;
	}

	public static String createDigest(String secret) {
		return createDigest(secret, createSalt());
	}
}
