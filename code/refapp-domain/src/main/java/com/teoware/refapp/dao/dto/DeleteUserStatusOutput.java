package com.teoware.refapp.dao.dto;

public class DeleteUserStatusOutput {

	private int rowsAffected;

	public DeleteUserStatusOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
