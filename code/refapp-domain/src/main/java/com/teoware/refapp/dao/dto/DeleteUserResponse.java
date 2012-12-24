package com.teoware.refapp.dao.dto;

public class DeleteUserResponse {

	private int rowsAffected;

	public DeleteUserResponse(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
