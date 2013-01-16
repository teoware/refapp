package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.UserInfo;

public class CreateUserInfoInput {

	private Id userId;
	private UserInfo userInfo;

	public CreateUserInfoInput() {
	}

	public CreateUserInfoInput(Id userId, UserInfo userInfo) {
		this.userId = userId;
		this.userInfo = userInfo;
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}
