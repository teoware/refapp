package com.teoware.refapp.model.user;

import javax.validation.constraints.NotNull;

import com.teoware.refapp.model.base.BaseBody;

public class Username extends BaseBody {

	@NotNull
	private String username;

	public Username() {
	}

	public Username(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
