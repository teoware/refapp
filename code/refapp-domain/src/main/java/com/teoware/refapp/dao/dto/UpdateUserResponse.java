package com.teoware.refapp.dao.dto;

public class UpdateUserResponse {

	private int rowsAffected;

	public UpdateUserResponse(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
