package com.teoware.refapp.dao.dto;

public class CreateTaskOutput extends CreateOutput {

	public CreateTaskOutput() {
		super();
	}

	public CreateTaskOutput(Id id, int rowsAffected) {
		super(id, rowsAffected);
	}
}
