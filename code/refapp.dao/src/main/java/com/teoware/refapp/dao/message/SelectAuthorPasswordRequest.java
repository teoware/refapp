package com.teoware.refapp.dao.message;

public class SelectAuthorPasswordRequest {

	private String userName;

	public SelectAuthorPasswordRequest() {
	}

	public SelectAuthorPasswordRequest(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
