package com.teoware.refapp.crypto;

public abstract class Digest extends Common {

    protected abstract org.bouncycastle.crypto.Digest createDigest();

    protected byte[] digestEncrypt(byte[] secret, byte[] salt) {
        org.bouncycastle.crypto.Digest digest = createDigest();
        if (digest == null) {
            throw new CryptoException("Digest is null");
        }
        byte[] data = hash(secret, salt);
        byte[] output = new byte[digest.getDigestSize()];
        digest.update(data, 0, data.length);
        digest.doFinal(output, 0);
        return output;

    }

    protected byte[] digestEncrypt(byte[] secret) {
        org.bouncycastle.crypto.Digest digest = createDigest();
        if (digest == null) {
            throw new CryptoException("Digest is null");
        }
        byte[] data = hash(secret);
        byte[] output = new byte[digest.getDigestSize()];
        digest.update(data, 0, data.length);
        digest.doFinal(output, 0);
        return output;

    }

    protected boolean digestVerify(byte[] secret, byte[] salt, byte[] hash) {
        if (hash == null) {
            throw new CryptoException("Hash is null");
        } else if (secret == null || salt == null) {
            return Boolean.FALSE;
        } else {
            return equals(digestEncrypt(secret, salt), hash);
        }
    }

    protected boolean digestVerify(byte[] secret, byte[] hash) {
        if (hash == null) {
            throw new CryptoException("Hash is null");
        } else if (secret == null) {
            return Boolean.FALSE;
        } else {
            return equals(digestEncrypt(secret), hash);
        }
    }

    private boolean equals(byte[] verify, byte[] hash) {
        if (verify == null) {
            return hash == null;
        } else {
            return toString(verify).equals(toString(hash));
        }
    }

    private byte[] hash(byte[] secret) {
        return Base64.encode(MAGIC_BYTES + toString(secret));
    }

    private byte[] hash(byte[] secret, byte[] salt) {
        return Base64.encode(MAGIC_BYTES + toString(secret) + toString(salt));
    }
}
