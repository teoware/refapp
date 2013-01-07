package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.UserPassword;

public class UpdateUserPasswordInput {

	private Id userId;
	private UserPassword userPassword;

	public UpdateUserPasswordInput() {
	}

	public UpdateUserPasswordInput(Id userId, UserPassword userPassword) {
		this.setUserId(userId);
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
