package com.teoware.refapp.dao.dto;

public class UpdateUserDetailsOutput {

	private int rowsAffected;

	public UpdateUserDetailsOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
