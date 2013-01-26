package com.teoware.refapp.crypto;

import java.io.UnsupportedEncodingException;

import org.bouncycastle.util.encoders.Base64;

public final class Digest {

	public static final String UTF8 = "UTF-8";

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

	public static byte[] base64Encode(byte[] data) {
		return Base64.encode(data);
	}

	public static byte[] base64Encode(String data) throws UnsupportedEncodingException {
		return base64Encode(fromString(data));
	}

	public static String toString(byte[] data) throws UnsupportedEncodingException {
		return new String(data, UTF8);
	}

	public static byte[] fromString(String data) throws UnsupportedEncodingException {
		return data.getBytes(UTF8);
	}
}
