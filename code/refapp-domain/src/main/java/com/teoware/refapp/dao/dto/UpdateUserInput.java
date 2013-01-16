package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.Username;

public class UpdateUserInput {

	private Id userId;
	private Username username;

	public UpdateUserInput() {
	}

	public UpdateUserInput(Id userId, Username username) {
		this.userId = userId;
		this.username = username;
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}

	public Username getUsername() {
		return username;
	}

	public void setUser(Username username) {
		this.username = username;
	}
}
