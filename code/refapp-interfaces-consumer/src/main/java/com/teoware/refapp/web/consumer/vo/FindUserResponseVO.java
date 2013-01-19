package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.user.User;

public class FindUserResponseVO {

	User user;

	public FindUserResponseVO(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
