package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.DESedeEngine;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PKCS7Padding;

public final class DES extends Cipher {

    private DES() {
    }

    public static byte[] encrypt(byte[] secret, byte[] key) {
        return create().process(secret, key, ENCRYPT);
    }

    public static byte[] decrypt(byte[] secret, byte[] key) {
        return create().process(secret, key, DECRYPT);
    }

    @Override
    protected BlockCipher createCipher() {
        return new DESedeEngine();
    }

    @Override
    protected BlockCipherPadding createCipherPadding() {
        return new PKCS7Padding();
    }

    static DES create() {
        return new DES();
    }
}
