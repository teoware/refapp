package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.digests.SHA512Digest;

public class SHA512 extends Digest {

	private SHA512() {
	}

	public static String encrypt(String secret) {
		SHA512Digest digest = new SHA512Digest();
		return encrypt(digest, secret);
	}

	public static String encrypt(String secret, String salt) {
		SHA512Digest digest = new SHA512Digest();
		return encrypt(digest, secret, salt);
	}

	public static boolean verify(String secret, String cipher) {
		SHA512Digest digest = new SHA512Digest();
		return verify(digest, secret, cipher);
	}

	public static boolean verify(String secret, String salt, String cipher) {
		SHA512Digest digest = new SHA512Digest();
		return verify(digest, secret, salt, cipher);
	}
}
