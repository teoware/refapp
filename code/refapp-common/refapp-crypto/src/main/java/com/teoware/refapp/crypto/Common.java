package com.teoware.refapp.crypto;

import java.nio.charset.Charset;

public abstract class Common {

    public static final Charset UTF8 = Charset.forName("UTF-8");
    protected static final String MAGIC_BYTES = "gandalf_";

    public static String toString(byte[] data) {
        return new String(data, UTF8);
    }

    public static byte[] fromString(String data) {
        return data.getBytes(UTF8);
    }
}
