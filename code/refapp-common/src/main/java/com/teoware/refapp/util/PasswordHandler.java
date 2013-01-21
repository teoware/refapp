package com.teoware.refapp.util;

import java.io.UnsupportedEncodingException;

import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.util.encoders.Base64;
import org.joda.time.DateTime;

import com.teoware.refapp.util.time.DateTimeParser;

public class PasswordHandler {

	public static final String UTF8 = "UTF-8";
	public static final String SHA1 = "SHA-1";
	public static final String BOUNCYCASTLE_PROVIDER = "BC";

	public static String encryptPassword(String password, String salt) throws UnsupportedEncodingException {
		String hash = toString(base64Encode(password)) + salt;
		byte[] data = fromString(hash);
		int len = data.length;
		byte[] output = new byte[len];
		SHA1Digest digest = new SHA1Digest();
		digest.update(data, 0, len);
		digest.doFinal(output, 0);
		return toString(base64Encode(output));

	}

	public static boolean verifyPassword(String password, String salt, String digest)
			throws UnsupportedEncodingException {
		if (password == null || salt == null || digest == null) {
			return Boolean.FALSE;
		} else {
			return digest.equals(encryptPassword(password, salt));
		}
	}

	public static String generateSalt() throws UnsupportedEncodingException {
		String dateTime = DateTimeParser.toString(DateTime.now(), DateTimeParser.DATE_PATTERN + " "
				+ DateTimeParser.TIME_PATTERN);
		return toString(base64Encode(dateTime));
	}

	private static byte[] base64Encode(byte[] data) {
		return Base64.encode(data);
	}

	private static byte[] base64Encode(String data) throws UnsupportedEncodingException {
		return base64Encode(fromString(data));
	}

	private static String toString(byte[] data) throws UnsupportedEncodingException {
		return new String(data, UTF8);
	}

	private static byte[] fromString(String data) throws UnsupportedEncodingException {
		return data.getBytes(UTF8);
	}
}
