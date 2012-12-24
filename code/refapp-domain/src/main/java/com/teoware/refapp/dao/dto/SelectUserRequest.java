package com.teoware.refapp.dao.dto;

public class SelectUserRequest {

	private String userName;

	public SelectUserRequest() {
	}

	public SelectUserRequest(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
