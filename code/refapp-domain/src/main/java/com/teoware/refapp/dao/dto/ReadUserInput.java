package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Username;

public class ReadUserInput {

	private Username username;

	public ReadUserInput() {
	}

	public ReadUserInput(Username username) {
		this.username = username;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
