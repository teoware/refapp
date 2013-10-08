package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.paddings.PKCS7Padding;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;

public class AES {

	private AES() {
	}

	public static byte[] encrypt(byte[] data, byte[] key) {
		BufferedBlockCipher cipher = cipher(key, Boolean.TRUE);
		return process(data, cipher);
	}

	public static byte[] decrypt(byte[] data, byte[] key) {
		BufferedBlockCipher cipher = cipher(key, Boolean.FALSE);
		return process(data, cipher);
	}

	private static byte[] process(byte[] data, BufferedBlockCipher cipher) {
		try {
			byte[] output = new byte[cipher.getOutputSize(data.length)];
			int length1 = cipher.processBytes(data, 0, data.length, output, 0);
			int length2;
			length2 = cipher.doFinal(output, length1);
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

	private static BufferedBlockCipher cipher(byte[] key, boolean encrypt) {
		PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new AESEngine(), new PKCS7Padding());
		cipher.init(encrypt, new KeyParameter(key));
		return cipher;
	}
}
