package com.teoware.refapp.crypto;

import org.junit.Test;

public class MD5Test {

    @Test
    public void test() {
        byte[] secret = AES.fromString("This is a secret");
        byte[] password = AES.fromString("This is the password");
        byte[] hash = AES.encrypt(secret, password);
        System.out.println(AES.toString(hash));
        byte[] data = AES.decrypt(hash, password);
        System.out.println(AES.toString(data));
    }
}
