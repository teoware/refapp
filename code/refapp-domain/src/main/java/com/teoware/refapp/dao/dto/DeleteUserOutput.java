package com.teoware.refapp.dao.dto;

public class DeleteUserOutput {

	private int rowsAffected;

	public DeleteUserOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
