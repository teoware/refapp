package com.teoware.refapp.crypto;

public final class UUID {

	private UUID() {
	}

	public static String generate() {
		return java.util.UUID.randomUUID().toString();
	}
}
