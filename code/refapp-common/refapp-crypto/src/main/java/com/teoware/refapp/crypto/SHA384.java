package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.digests.SHA384Digest;

public final class SHA384 extends Digest {

    private SHA384() {
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
        return new SHA384Digest();
    }

    private static SHA384 create() {
        return new SHA384();
    }
}
