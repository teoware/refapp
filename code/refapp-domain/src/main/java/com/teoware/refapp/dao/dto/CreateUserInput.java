package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.User;

public class CreateUserInput {

	private User user;

	public CreateUserInput() {
	}

	public CreateUserInput(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
