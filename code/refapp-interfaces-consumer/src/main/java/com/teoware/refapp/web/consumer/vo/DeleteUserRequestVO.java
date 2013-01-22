package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.common.Username;

public class DeleteUserRequestVO {

	private Username username;

	public DeleteUserRequestVO(Username username) {
		this.username = username;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
