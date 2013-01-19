package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.Username;

public class DeleteUserPasswordRequestVO {

	private UserPassword userPassword;
	private Username username;

	public DeleteUserPasswordRequestVO(UserPassword userPassword, Username username) {
		this.userPassword = userPassword;
		this.username = username;
	}

	public UserPassword getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(UserPassword userPassword) {
		this.userPassword = userPassword;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
