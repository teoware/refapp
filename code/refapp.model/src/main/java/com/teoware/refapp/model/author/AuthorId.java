package com.teoware.refapp.model.author;

public class AuthorId {

	private String userName;
	private String password;

	public AuthorId() {
	}

	public AuthorId(String userName) {
		this.userName = userName;
	}

	public AuthorId(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
