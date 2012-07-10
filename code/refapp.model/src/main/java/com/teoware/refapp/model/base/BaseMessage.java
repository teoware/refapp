package com.teoware.refapp.model.base;

public abstract class BaseMessage<H extends BaseHeader, B extends BaseBody> {

	protected H header;
	protected B body;

	public BaseMessage(H header, B body) {
		this.header = header;
		this.body = body;
	}

	public H getHeader() {
		return header;
	}

	public B getBody() {
		return body;
	}
}
