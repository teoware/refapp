package com.teoware.refapp.dao.dto;

public class UpdateUserInfoOutput {

	private int rowsAffected;

	public UpdateUserInfoOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
