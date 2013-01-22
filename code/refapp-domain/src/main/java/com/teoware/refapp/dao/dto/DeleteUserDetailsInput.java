package com.teoware.refapp.dao.dto;

public class DeleteUserDetailsInput {

	private Id userId;

	public DeleteUserDetailsInput() {
	}

	public DeleteUserDetailsInput(Id userId) {
		this.setUserId(userId);
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}
}
