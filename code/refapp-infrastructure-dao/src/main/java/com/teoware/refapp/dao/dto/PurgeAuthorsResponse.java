package com.teoware.refapp.dao.dto;

public class PurgeAuthorsResponse {

	private int rowsAffected;

	public PurgeAuthorsResponse(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
