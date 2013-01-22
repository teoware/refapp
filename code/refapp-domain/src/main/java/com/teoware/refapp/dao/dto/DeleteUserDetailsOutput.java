package com.teoware.refapp.dao.dto;

public class DeleteUserDetailsOutput {

	private int rowsAffected;

	public DeleteUserDetailsOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
