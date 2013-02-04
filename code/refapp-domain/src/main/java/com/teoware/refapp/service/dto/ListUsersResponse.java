package com.teoware.refapp.service.dto;

import java.util.List;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.user.User;

public class ListUsersResponse {

	private Header header;
	private List<User> userList;

	public ListUsersResponse(Header header, List<User> userList) {
		this.header = header;
		this.userList = userList;
	}

	public Header getHeader() {
		return header;
	}

	public List<User> getBody() {
		return userList;
	}
}
