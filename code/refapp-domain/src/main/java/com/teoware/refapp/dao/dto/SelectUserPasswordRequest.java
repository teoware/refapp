package com.teoware.refapp.dao.dto;

public class SelectUserPasswordRequest {

	private String userName;

	public SelectUserPasswordRequest() {
	}

	public SelectUserPasswordRequest(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
