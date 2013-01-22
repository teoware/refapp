package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.common.Username;

public class ActivateUserRequestVO {

	private Username username;

	public ActivateUserRequestVO(Username username) {
		this.username = username;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
