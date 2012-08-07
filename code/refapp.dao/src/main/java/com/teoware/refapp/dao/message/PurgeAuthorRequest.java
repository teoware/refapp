package com.teoware.refapp.dao.message;

public class PurgeAuthorRequest {

	private String userName;

	public PurgeAuthorRequest() {
	}

	public PurgeAuthorRequest(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
