package com.teoware.refapp.dao.dto;

public class DeleteUserPasswordInput {

	private Id userId;

	public DeleteUserPasswordInput() {
	}

	public DeleteUserPasswordInput(Id userId) {
		this.setUserId(userId);
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}
}
