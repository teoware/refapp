package com.teoware.refapp.dao.dto;

import java.util.List;

import com.teoware.refapp.model.user.User;

public class ReadNoteOutput {

	List<User> userList;

	public ReadNoteOutput(List<User> userList) {
		this.userList = userList;
	}

	public List<User> getUserList() {
		return userList;
	}
}
