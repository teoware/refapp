package com.teoware.refapp.dao.dto;

public class UpdateUserPasswordOutput {

	private int rowsAffected;

	public UpdateUserPasswordOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
