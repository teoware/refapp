package com.teoware.refapp.dao.dto;

public class ReadUserPasswordInput {

	private String userName;

	public ReadUserPasswordInput() {
	}

	public ReadUserPasswordInput(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
