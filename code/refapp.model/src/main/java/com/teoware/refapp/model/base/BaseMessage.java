package com.teoware.refapp.model.base;

import javax.validation.constraints.NotNull;

public abstract class BaseMessage<H extends BaseHeader, B extends BaseBody> {

	@NotNull
	protected H header;
	@NotNull
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
