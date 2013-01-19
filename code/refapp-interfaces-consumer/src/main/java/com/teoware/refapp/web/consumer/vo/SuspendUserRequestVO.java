package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.user.Username;

public class SuspendUserRequestVO {

	private Username username;

	public SuspendUserRequestVO(Username username) {
		this.username = username;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
