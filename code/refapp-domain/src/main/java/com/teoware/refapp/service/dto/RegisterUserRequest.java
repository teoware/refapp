package com.teoware.refapp.service.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;

public class RegisterUserRequest extends BaseMessage<Header, User> {

	@NotNull
	@Valid
	private UserPassword userPassword;

	public RegisterUserRequest(Header header, User user, UserPassword userPassword) {
		super(header, user);
		this.userPassword = userPassword;
	}

	public RegisterUserRequest(User user, UserPassword userPassword) {
		this(Header.getInstance(), user, userPassword);
	}

	public UserPassword getUserPassword() {
		return userPassword;
	}
}
