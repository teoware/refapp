package com.teoware.refapp.service.dto;

import java.util.List;

import com.teoware.refapp.model.user.User;

public class ListUsersResponse {

	private List<User> userList;

	public ListUsersResponse(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getUserList() {
		return userList;
	}
}
