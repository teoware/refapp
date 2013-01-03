package com.teoware.refapp.dao.dto;

public class PurgeUsersOutput {

	private int rowsAffected;

	public PurgeUsersOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
