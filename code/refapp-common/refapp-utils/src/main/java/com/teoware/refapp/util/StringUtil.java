package com.teoware.refapp.util;

import java.nio.charset.Charset;

public final class StringUtil {

    public static final String UTF_8 = "UTF-8";
    public static final Charset UTF_8_CHARSET = Charset.forName(UTF_8);

    private StringUtil() {
    }

    public static String fromBytes(byte[] bytes) {
        if (bytes != null) {
            return new String(bytes, StringUtil.UTF_8_CHARSET);
        } else {
            return null;
        }
    }

    public static byte[] toBytes(String string) {
        if (string != null) {
            return string.getBytes(StringUtil.UTF_8_CHARSET);
        } else {
            return null;
        }
    }
}
