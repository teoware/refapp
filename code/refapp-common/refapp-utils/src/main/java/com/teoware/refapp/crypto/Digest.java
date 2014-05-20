package com.teoware.refapp.crypto;

public abstract class Digest {

    protected static String encrypt(org.bouncycastle.crypto.Digest digest, String secret, String salt) {
        if (digest == null) {
            throw new CryptoException("Digest is null");
        }
        String hash = hash(secret, salt);
        byte[] data = Common.fromString(hash);
        int len = data.length;
        byte[] output = new byte[len];
        digest.update(data, 0, len);
        digest.doFinal(output, 0);
        return Common.toString(Base64.encode(output));

    }

    protected static String encrypt(org.bouncycastle.crypto.Digest digest, String secret) {
        if (digest == null) {
            throw new CryptoException("Digest is null");
        }
        byte[] data = Common.fromString(secret);
        int len = data.length;
        byte[] output = new byte[len];
        digest.update(data, 0, len);
        digest.doFinal(output, 0);
        return Common.toString(Base64.encode(output));

    }

    protected static boolean verify(org.bouncycastle.crypto.Digest digest, String secret, String salt, String cipher) {
        if (secret == null || salt == null || cipher == null) {
            return Boolean.FALSE;
        } else {
            return cipher.equals(encrypt(digest, secret, salt));
        }
    }

    protected static boolean verify(org.bouncycastle.crypto.Digest digest, String secret, String cipher) {
        if (secret == null || cipher == null) {
            return Boolean.FALSE;
        } else {
            return cipher.equals(encrypt(digest, secret));
        }
    }

    private static String hash(String secret, String salt) {
        return Common.toString(Base64.encode(secret + salt));
    }
}
