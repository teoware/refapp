package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.user.Username;

public class FindUserRequestVO {

	private Username username;

	public FindUserRequestVO() {
	}

	public FindUserRequestVO(Username username) {
		this.username = username;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
