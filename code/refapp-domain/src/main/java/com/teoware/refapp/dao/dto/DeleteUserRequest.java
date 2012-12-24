package com.teoware.refapp.dao.dto;

public class DeleteUserRequest {

	private String userName;

	public DeleteUserRequest() {
	}

	public DeleteUserRequest(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
