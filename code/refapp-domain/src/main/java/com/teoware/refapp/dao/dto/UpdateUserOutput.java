package com.teoware.refapp.dao.dto;

public class UpdateUserOutput {

	private Id userId;
	private int rowsAffected;

	public UpdateUserOutput(Id userId, int rowsAffected) {
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
