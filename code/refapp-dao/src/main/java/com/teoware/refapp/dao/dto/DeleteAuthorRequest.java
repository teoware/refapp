package com.teoware.refapp.dao.dto;

public class DeleteAuthorRequest {

	private String userName;

	public DeleteAuthorRequest() {
	}

	public DeleteAuthorRequest(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
