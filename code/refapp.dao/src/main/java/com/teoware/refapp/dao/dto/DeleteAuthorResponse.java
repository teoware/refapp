package com.teoware.refapp.dao.dto;

public class DeleteAuthorResponse {

	private int rowsAffected;

	public DeleteAuthorResponse(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
