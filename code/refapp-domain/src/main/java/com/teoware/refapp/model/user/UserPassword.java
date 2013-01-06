package com.teoware.refapp.model.user;

import javax.validation.constraints.NotNull;

public class UserPassword {

	@NotNull
	private String password;

	@NotNull
	private String salt;

	public UserPassword() {
	}

	public UserPassword(String password, String salt) {
		this.password = password;
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
