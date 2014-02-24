package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.digests.MD5Digest;

public class MD5 extends Digest {

    private MD5() {
    }

    public static String encrypt(String secret) {
        MD5Digest digest = new MD5Digest();
        return encrypt(digest, secret);
    }

    public static String encrypt(String secret, String salt) {
        MD5Digest digest = new MD5Digest();
        return encrypt(digest, secret, salt);
    }

    public static boolean verify(String secret, String cipher) {
        MD5Digest digest = new MD5Digest();
        return verify(digest, secret, cipher);
    }

    public static boolean verify(String secret, String salt, String cipher) {
        MD5Digest digest = new MD5Digest();
        return verify(digest, secret, salt, cipher);
    }
}
