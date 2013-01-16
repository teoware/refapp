package com.teoware.refapp.dao.dto;

public class UpdateUserStatusOutput {

	private int rowsAffected;

	public UpdateUserStatusOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
