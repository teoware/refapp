package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.digests.SHA512Digest;

public final class SHA512 extends Digest {

    private SHA512() {
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
        return new SHA512Digest();
    }

    private static SHA512 create() {
        return new SHA512();
    }
}
