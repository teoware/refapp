package com.teoware.refapp.web.consumer.vo;

import java.util.List;

import com.teoware.refapp.model.user.User;

public class ListUsersVO {

	private List<User> userList;

	public ListUsersVO(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
