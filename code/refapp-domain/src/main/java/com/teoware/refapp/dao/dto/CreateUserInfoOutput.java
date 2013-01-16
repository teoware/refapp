package com.teoware.refapp.dao.dto;

public class CreateUserInfoOutput {

	private int rowsAffected;

	public CreateUserInfoOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
