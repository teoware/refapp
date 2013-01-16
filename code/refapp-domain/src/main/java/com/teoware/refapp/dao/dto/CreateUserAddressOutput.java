package com.teoware.refapp.dao.dto;

public class CreateUserAddressOutput {

	private int rowsAffected;

	public CreateUserAddressOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
