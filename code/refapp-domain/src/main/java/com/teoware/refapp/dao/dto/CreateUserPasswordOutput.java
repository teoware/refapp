package com.teoware.refapp.dao.dto;

public class CreateUserPasswordOutput {

	private int rowsAffected;

	public CreateUserPasswordOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
