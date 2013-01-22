package com.teoware.refapp.dao.dto;

import java.util.List;

import com.teoware.refapp.model.user.User;

public class ReadTaskOutput {

	List<User> userList;

	public ReadTaskOutput(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getUserList() {
		return userList;
	}
}
