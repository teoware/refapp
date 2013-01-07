package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserPassword;

public class ChangeUserPasswordRequest extends BaseMessage<Header, UserPassword> {

	public ChangeUserPasswordRequest(Header header, UserPassword userPassword) {
		super(header, userPassword);
	}

	public ChangeUserPasswordRequest(User user, UserPassword userPassword) {
		this(Header.getInstance(), userPassword);
	}
}
