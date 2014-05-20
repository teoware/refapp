package com.teoware.refapp.crypto;

import java.io.UnsupportedEncodingException;

public class Common {

    public static final String UTF8 = "UTF-8";
    public static final String SHA1 = "SHA-1";
    public static final String BOUNCYCASTLE_PROVIDER = "BC";

    private Common() {
    }

    public static String toString(byte[] data) {
        try {
            return new String(data, UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new CryptoException("Unsupported encoding", e);
        }
    }

    public static byte[] fromString(String data) {
        try {
            return data.getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            throw new CryptoException("Unsupported encoding", e);
        }
    }
}
