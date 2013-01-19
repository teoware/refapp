package com.teoware.refapp.dao.dto;

public class DeleteUserInfoInput {

	private Id userId;

	public DeleteUserInfoInput() {
	}

	public DeleteUserInfoInput(Id userId) {
		this.setUserId(userId);
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}
}
