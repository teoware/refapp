package com.teoware.refapp.dao.dto;

public class DeleteUserInput {

	private String userName;

	public DeleteUserInput() {
	}

	public DeleteUserInput(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
