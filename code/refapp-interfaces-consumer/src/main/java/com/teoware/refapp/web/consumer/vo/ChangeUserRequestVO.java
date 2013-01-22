package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.common.Username;
import com.teoware.refapp.model.user.User;

public class ChangeUserRequestVO {

	private User user;
	private Username username;

	public ChangeUserRequestVO(User user, Username username) {
		this.user = user;
		this.username = username;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
