package com.teoware.refapp.dao.dto;

public class UpdateUserOutput {

	private int rowsAffected;

	public UpdateUserOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
