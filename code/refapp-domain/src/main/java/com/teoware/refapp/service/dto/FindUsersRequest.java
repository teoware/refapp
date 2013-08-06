package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;

public class FindUsersRequest {

	private Header header;

	public FindUsersRequest() {
		this.setHeader(Header.getInstance());
	}

	public FindUsersRequest(Header header) {
		this.setHeader(header);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
}
