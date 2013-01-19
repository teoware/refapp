package com.teoware.refapp.dao.dto;

public class DeleteUserInfoOutput {

	private int rowsAffected;

	public DeleteUserInfoOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
