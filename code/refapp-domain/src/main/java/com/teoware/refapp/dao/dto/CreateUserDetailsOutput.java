package com.teoware.refapp.dao.dto;

public class CreateUserDetailsOutput {

	private int rowsAffected;

	public CreateUserDetailsOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}
}
