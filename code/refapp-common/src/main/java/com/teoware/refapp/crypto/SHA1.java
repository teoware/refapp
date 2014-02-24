package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.digests.SHA1Digest;

public class SHA1 extends Digest {

    private SHA1() {
    }

    public static String encrypt(String secret) {
        SHA1Digest digest = new SHA1Digest();
        return encrypt(digest, secret);
    }

    public static String encrypt(String secret, String salt) {
        SHA1Digest digest = new SHA1Digest();
        return encrypt(digest, secret, salt);
    }

    public static boolean verify(String secret, String cipher) {
        SHA1Digest digest = new SHA1Digest();
        return verify(digest, secret, cipher);
    }

    public static boolean verify(String secret, String salt, String cipher) {
        SHA1Digest digest = new SHA1Digest();
        return verify(digest, secret, salt, cipher);
    }
}
