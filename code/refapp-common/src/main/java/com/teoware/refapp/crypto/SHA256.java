package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.digests.SHA256Digest;

public class SHA256 extends Digest {

	private SHA256() {
	}

	public static String encrypt(String secret) {
		SHA256Digest digest = new SHA256Digest();
		return encrypt(digest, secret);
	}

	public static String encrypt(String secret, String salt) {
		SHA256Digest digest = new SHA256Digest();
		return encrypt(digest, secret, salt);
	}

	public static boolean verify(String secret, String cipher) {
		SHA256Digest digest = new SHA256Digest();
		return verify(digest, secret, cipher);
	}

	public static boolean verify(String secret, String salt, String cipher) {
		SHA256Digest digest = new SHA256Digest();
		return verify(digest, secret, salt, cipher);
	}
}
