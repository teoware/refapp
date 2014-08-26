package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.paddings.BlockCipherPadding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;

import java.util.Arrays;

public abstract class Cipher extends Common {

    protected static final boolean ENCRYPT = Boolean.TRUE;
    protected static final boolean DECRYPT = Boolean.FALSE;
    private static final int KEY_SIZE = 32; // 256 bits

    protected abstract BlockCipher createCipher();

    protected abstract BlockCipherPadding createCipherPadding();

    protected byte[] process(byte[] secret, byte[] key, boolean encrypt) {
        try {
            BufferedBlockCipher cipher = createBufferedCipher(key, encrypt);
            byte[] output = new byte[cipher.getOutputSize(secret.length)];
            int length1 = cipher.processBytes(secret, 0, secret.length, output, 0);
            int length2 = cipher.doFinal(output, length1);
            byte[] result = new byte[length1 + length2];
            System.arraycopy(output, 0, result, 0, result.length);
            return result;
        } catch (DataLengthException e) {
            throw new CryptoException("Wrong data length", e);
        } catch (IllegalStateException e) {
            throw new CryptoException("Illegal state", e);
        } catch (InvalidCipherTextException e) {
            throw new CryptoException("Invalid cipher text", e);
        }
    }

    private BufferedBlockCipher createBufferedCipher(byte[] key, boolean encrypt) {
        try {
            BufferedBlockCipher bufferedCipher = createPaddedBufferedCipher();
            bufferedCipher.init(encrypt, createKeyParameter(key));
            return bufferedCipher;
        } catch (IllegalArgumentException e) {
            throw new CryptoException("Invalid cipher key", e);
        }
    }

    private PaddedBufferedBlockCipher createPaddedBufferedCipher() {
        return new PaddedBufferedBlockCipher(createCipher(), createCipherPadding());
    }

    private KeyParameter createKeyParameter(byte[] key) {
        byte[] verifiedKey = verifyKey(key);
        return new KeyParameter(verifiedKey);
    }

    private byte[] verifyKey(byte[] key) {
        if (key == null) {
            throw new CryptoException("Key is null");
        }
        if (key.length > KEY_SIZE) {
            return Arrays.copyOf(key, KEY_SIZE);
        } else if (key.length < KEY_SIZE) {
            return Arrays.copyOf(SHA1.encrypt(key), KEY_SIZE);
        } else {
            return key;
        }
    }
}
