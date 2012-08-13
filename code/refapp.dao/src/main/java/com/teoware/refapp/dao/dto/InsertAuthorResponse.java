package com.teoware.refapp.dao.dto;

public class InsertAuthorResponse {

	private int rowsAffected;

	public InsertAuthorResponse(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
