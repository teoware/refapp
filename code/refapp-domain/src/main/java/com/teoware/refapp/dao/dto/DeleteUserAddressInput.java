package com.teoware.refapp.dao.dto;

public class DeleteUserAddressInput {

	private Id userId;

	public DeleteUserAddressInput() {
	}

	public DeleteUserAddressInput(Id userId) {
		this.setUserId(userId);
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}
}
