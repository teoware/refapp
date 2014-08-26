package com.teoware.refapp.crypto;

import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.crypto.prng.RandomGenerator;

import java.util.UUID;

public final class Random {

    private Random() {
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static byte[] random(int size) {
        byte[] random = new byte[size];
        generator().nextBytes(random);
        return random;
    }

    private static RandomGenerator generator() {
        return new DigestRandomGenerator(new SHA512Digest());
    }
}
