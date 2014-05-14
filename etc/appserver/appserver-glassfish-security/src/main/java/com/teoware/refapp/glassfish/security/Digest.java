package com.teoware.refapp.glassfish.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Digest {

    private final static Logger LOGGER = Logger.getLogger(Digest.class.getName());

    private static final String CHARSET = "UTF-8";
    private static final String ENCRYPTION_ALGORITHM = "SHA-512";

    private Digest() {
    }

    public static byte[] createSalt(int length) {
        byte bytes[] = new byte[length];
        SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
        return bytes;
    }

    public static byte[] digest(String password, byte[] salt) {
        byte[] hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance(ENCRYPTION_ALGORITHM);
            digest.reset();
            digest.update(salt);
            digest.update(password.getBytes(CHARSET));
            hash = digest.digest();
        } catch (UnsupportedEncodingException e) {
            LOGGER.log(Level.SEVERE, "Encoding Problem", e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "Encoding Problem", e);
        }
        return hash;
    }
}
