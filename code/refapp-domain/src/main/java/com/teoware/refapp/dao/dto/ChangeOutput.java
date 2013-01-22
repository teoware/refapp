package com.teoware.refapp.dao.dto;

public abstract class ChangeOutput {

	protected int rowsAffected;

	protected ChangeOutput() {
	}

	protected ChangeOutput(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}

	public int getRowsAffected() {
		return rowsAffected;
	}

	public void setRowsAffected(int rowsAffected) {
		this.rowsAffected = rowsAffected;
	}
}
