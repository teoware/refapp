package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.Username;

public class SelectUserRequest {

	private Username username;

	public SelectUserRequest() {
	}

	public SelectUserRequest(Username username) {
		this.username = username;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
