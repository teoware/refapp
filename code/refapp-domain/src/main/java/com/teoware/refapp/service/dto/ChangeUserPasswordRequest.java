package com.teoware.refapp.service.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.Username;

public class ChangeUserPasswordRequest extends BaseMessage<Header, UserPassword> {

	@NotNull
	@Valid
	private Username username;

	public ChangeUserPasswordRequest(Header header, UserPassword userPassword, Username username) {
		super(header, userPassword);
		this.username = username;
	}

	public ChangeUserPasswordRequest(User user, UserPassword userPassword, Username username) {
		this(Header.getInstance(), userPassword, username);
	}

	public Username getUsername() {
		return username;
	}
}
