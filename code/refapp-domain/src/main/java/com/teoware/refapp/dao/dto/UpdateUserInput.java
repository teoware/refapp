package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.User;

public class UpdateUserInput {

	private User user;

	public UpdateUserInput() {
	}

	public UpdateUserInput(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
