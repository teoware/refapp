package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;

public class ListUsersRequest {

	private Header header;

	public ListUsersRequest() {
		this.setHeader(Header.getInstance());
	}

	public ListUsersRequest(Header header) {
		this.setHeader(header);
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}
}
