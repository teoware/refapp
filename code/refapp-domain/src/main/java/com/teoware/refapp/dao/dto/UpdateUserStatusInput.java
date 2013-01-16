package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.UserStatus;

public class UpdateUserStatusInput {

	private Id userId;
	private UserStatus userStatus;

	public UpdateUserStatusInput() {
	}

	public UpdateUserStatusInput(Id userId, UserStatus userStatus) {
		this.userId = userId;
		this.userStatus = userStatus;
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
}
