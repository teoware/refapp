package com.teoware.refapp.util;

import java.util.UUID;

public class UUIDGenerator {

	public static UUID createUUID() {
		return UUID.randomUUID();
	}

	public static String createUUIDString() {
		return createUUID().toString();
	}
}
