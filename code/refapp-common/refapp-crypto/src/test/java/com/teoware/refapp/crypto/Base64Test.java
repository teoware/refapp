package com.teoware.refapp.crypto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Base64Test {

    @Test
    public void testTwoBase64CodesAreTheSame() {
        byte[] code1 = Base64.encode("abcd");
        byte[] code2 = Base64.encode("abcd");

        assertEquals(code1.length, code2.length);
        for (int i = 0; i < code1.length; i++) {
            assertEquals(code1[i], code2[i]);
        }
        assertEquals(new String(code1), new String(code2));
    }
}
