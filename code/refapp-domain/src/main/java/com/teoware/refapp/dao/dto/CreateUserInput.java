package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Username;

public class CreateUserInput {

	private Username username;

	public CreateUserInput() {
	}

	public CreateUserInput(Username username) {
		this.username = username;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
