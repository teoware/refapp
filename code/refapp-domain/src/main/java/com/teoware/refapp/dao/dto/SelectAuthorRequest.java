package com.teoware.refapp.dao.dto;

public class SelectAuthorRequest {

	private String userName;

	public SelectAuthorRequest() {
	}

	public SelectAuthorRequest(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
