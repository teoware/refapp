package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.UserPassword;

public class CreateUserPasswordInput extends ChangeInput {

	private UserPassword userPassword;

	public CreateUserPasswordInput() {
	}

	public CreateUserPasswordInput(Id id, UserPassword userPassword) {
		super(id);
		this.userPassword = userPassword;
	}

	public UserPassword getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(UserPassword userPassword) {
		this.userPassword = userPassword;
	}
}
