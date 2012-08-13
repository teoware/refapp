package com.teoware.refapp.dao.dto;

public class UpdateAuthorResponse {

	private int rowsAffected;

	public UpdateAuthorResponse(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
