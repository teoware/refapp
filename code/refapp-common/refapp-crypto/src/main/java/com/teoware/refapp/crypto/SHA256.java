package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.digests.SHA256Digest;

public final class SHA256 extends Digest {

    private SHA256() {
    }

    public static byte[] encrypt(byte[] secret) {
        return create().digestEncrypt(secret);
    }

    public static byte[] encrypt(byte[] secret, byte[] salt) {
        return create().digestEncrypt(secret, salt);
    }

    public static boolean verify(byte[] secret, byte[] hash) {
        return create().digestVerify(secret, hash);
    }

    public static boolean verify(byte[] secret, byte[] salt, byte[] hash) {
        return create().digestVerify(secret, salt, hash);
    }

    @Override
    protected org.bouncycastle.crypto.Digest createDigest() {
        return new SHA256Digest();
    }

    private static SHA256 create() {
        return new SHA256();
    }
}
