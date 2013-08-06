package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.enums.Status;

public class ReadUsersInput {

	private Status status;

	public ReadUsersInput() {
	}

	public ReadUsersInput(Status status) {
		this.status = status;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
