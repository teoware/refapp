package com.teoware.refapp.dao.dto;

public class InsertUserResponse {

	private int rowsAffected;

	public InsertUserResponse(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
