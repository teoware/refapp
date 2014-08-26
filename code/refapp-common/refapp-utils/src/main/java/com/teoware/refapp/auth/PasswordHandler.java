package com.teoware.refapp.auth;


import com.teoware.refapp.crypto.Random;
import com.teoware.refapp.crypto.SHA1;

import static com.teoware.refapp.util.StringUtil.fromBytes;
import static com.teoware.refapp.util.StringUtil.toBytes;

public class PasswordHandler {

    private static final int SALT_SIZE = 20;

    public static String encryptPassword(String password, String salt) {
        byte[] digest = SHA1.encrypt(toBytes(password), toBytes(salt));
        return fromBytes(digest);
    }

    public static boolean verifyPassword(String password, String salt, String digest) {
        if (password == null || salt == null || digest == null) {
            return Boolean.FALSE;
        } else {
            return digest.equals(encryptPassword(password, salt));
        }
    }

    public static String generateSalt() {
        return fromBytes(Random.random(SALT_SIZE));
    }
}
