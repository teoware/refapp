package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.digests.SHA224Digest;

public class SHA224 extends Digest {

    private SHA224() {
    }

    public static String encrypt(String secret) {
        SHA224Digest digest = new SHA224Digest();
        return encrypt(digest, secret);
    }

    public static String encrypt(String secret, String salt) {
        SHA224Digest digest = new SHA224Digest();
        return encrypt(digest, secret, salt);
    }

    public static boolean verify(String secret, String cipher) {
        SHA224Digest digest = new SHA224Digest();
        return verify(digest, secret, cipher);
    }

    public static boolean verify(String secret, String salt, String cipher) {
        SHA224Digest digest = new SHA224Digest();
        return verify(digest, secret, salt, cipher);
    }
}
