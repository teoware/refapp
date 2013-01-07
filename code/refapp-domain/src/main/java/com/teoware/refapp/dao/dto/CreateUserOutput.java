package com.teoware.refapp.dao.dto;

public class CreateUserOutput {

	private Id userId;
	private int rowsAffected;

	public CreateUserOutput(Id userId, int rowsAffected) {
		this.userId = userId;
		this.rowsAffected = rowsAffected;
	}

	public Id getUserId() {
		return userId;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
