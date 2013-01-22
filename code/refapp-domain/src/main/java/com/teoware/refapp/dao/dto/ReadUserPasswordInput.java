package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Username;

public class ReadUserPasswordInput {

	private Username username;

	public ReadUserPasswordInput() {
	}

	public ReadUserPasswordInput(Username username) {
		this.username = username;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
