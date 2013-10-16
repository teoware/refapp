package com.teoware.refapp.glassfish.security;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public final class Base64 {

	private Base64() {
	}

	private final static Logger LOGGER = Logger.getLogger(Base64.class.getName());

	public static String encode(byte[] text) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(text);
	}

	public static byte[] decode(String text) {
		byte[] textBytes = null;
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			textBytes = decoder.decodeBuffer(text);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Base64 decoding failed", e);
		}
		return textBytes;
	}
}
