package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.digests.MD5Digest;

public final class MD5 extends Digest {

    private MD5() {
    }

    public static byte[] encrypt(byte[] secret) {
        return create().encrypt(secret);
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
        return new MD5Digest();
    }

    static MD5 create() {
        return new MD5();
    }
}
