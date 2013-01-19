package com.teoware.refapp.dao.dto;

public class DeleteUserPasswordOutput {

	private int rowsAffected;

	public DeleteUserPasswordOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
