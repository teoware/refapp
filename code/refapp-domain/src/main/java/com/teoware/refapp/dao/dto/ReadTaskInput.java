package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Username;

public class ReadTaskInput {

	private Username username;

	public ReadTaskInput() {
	}

	public ReadTaskInput(Username username) {
		this.username = username;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
