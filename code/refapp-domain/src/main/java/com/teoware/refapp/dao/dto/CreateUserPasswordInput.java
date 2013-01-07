package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.UserPassword;

public class CreateUserPasswordInput {

	private Id userId;
	private UserPassword userPassword;

	public CreateUserPasswordInput() {
	}

	public CreateUserPasswordInput(Id userId, UserPassword userPassword) {
		this.userId = userId;
		this.userPassword = userPassword;
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}

	public UserPassword getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(UserPassword userPassword) {
		this.userPassword = userPassword;
	}
}
