package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PKCS7Padding;

public final class AES extends Cipher {

    private AES() {
    }

    public static byte[] encrypt(byte[] secret, byte[] key) {
        return create().process(secret, key, ENCRYPT);
    }

    public static byte[] decrypt(byte[] secret, byte[] key) {
        return create().process(secret, key, DECRYPT);
    }

    @Override
    protected BlockCipher createCipher() {
        return new AESEngine();
    }

    @Override
    protected BlockCipherPadding createCipherPadding() {
        return new PKCS7Padding();
    }

    private static AES create() {
        return new AES();
    }
}
