package com.teoware.refapp.dao.dto;

public class DeleteUserStatusInput {

	private Id userId;

	public DeleteUserStatusInput() {
	}

	public DeleteUserStatusInput(Id userId) {
		this.setUserId(userId);
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}
}
