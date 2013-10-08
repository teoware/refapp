package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.digests.SHA384Digest;

public class SHA384 extends Digest {

	private SHA384() {
	}

	public static String encrypt(String secret) {
		SHA384Digest digest = new SHA384Digest();
		return encrypt(digest, secret);
	}

	public static String encrypt(String secret, String salt) {
		SHA384Digest digest = new SHA384Digest();
		return encrypt(digest, secret, salt);
	}

	public static boolean verify(String secret, String cipher) {
		SHA384Digest digest = new SHA384Digest();
		return verify(digest, secret, cipher);
	}

	public static boolean verify(String secret, String salt, String cipher) {
		SHA384Digest digest = new SHA384Digest();
		return verify(digest, secret, salt, cipher);
	}
}
