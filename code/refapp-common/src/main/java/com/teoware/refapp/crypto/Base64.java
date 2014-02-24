package com.teoware.refapp.crypto;

public class Base64 {

    private Base64() {
    }

    public static byte[] encode(byte[] data) {
        return org.bouncycastle.util.encoders.Base64.encode(data);
    }

    public static byte[] encode(String data) {
        return encode(Common.fromString(data));
    }
}
