package com.teoware.refapp.dao.dto;

public class DeleteUserAddressOutput {

	private int rowsAffected;

	public DeleteUserAddressOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
