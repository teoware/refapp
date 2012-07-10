package com.teoware.refapp.model;

import java.util.UUID;

public class MessageId {

	private String uuid;

	public MessageId(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public static MessageId getInstance() {
		return new MessageId(UUID.randomUUID().toString());
	}
}
