package com.teoware.refapp.dao.dto;

public class PurgeUsersResponse {

	private int rowsAffected;

	public PurgeUsersResponse(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
