package com.teoware.refapp.dao.dto;

public class DeleteUserInput {

	private Id userId;

	public DeleteUserInput() {
	}

	public DeleteUserInput(Id userId) {
		this.setUserId(userId);
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}
}
