package com.teoware.refapp.dao.dto;

public class UpdateUserAddressOutput {

	private int rowsAffected;

	public UpdateUserAddressOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
