package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.user.User;

public class UserVO {

	User user;

	public UserVO(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
