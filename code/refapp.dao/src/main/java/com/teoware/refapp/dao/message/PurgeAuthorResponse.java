package com.teoware.refapp.dao.message;

public class PurgeAuthorResponse {

	private int rowsAffected;

	public PurgeAuthorResponse(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
