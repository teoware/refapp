package com.teoware.refapp.model;

import com.teoware.refapp.model.base.BaseHeader;

public class Header extends BaseHeader {

	private MessageId messageId;
	private Timestamp timestamp;

	public Header(MessageId messageId, Timestamp timestamp) {
		super();
		this.messageId = messageId;
		this.timestamp = timestamp;
	}

	public MessageId getMessageId() {
		return messageId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public static Header getInstance() {
		return new Header(MessageId.getInstance(), Timestamp.getInstance());
	}
}
