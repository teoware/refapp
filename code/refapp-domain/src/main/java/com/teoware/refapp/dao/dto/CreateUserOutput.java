package com.teoware.refapp.dao.dto;

public class CreateUserOutput {

	private int rowsAffected;

	public CreateUserOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
