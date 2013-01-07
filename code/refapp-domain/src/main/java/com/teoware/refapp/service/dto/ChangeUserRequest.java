package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.user.User;

public class ChangeUserRequest extends BaseMessage<Header, User> {

	public ChangeUserRequest(Header header, User user) {
		super(header, user);
	}

	public ChangeUserRequest(User user) {
		this(Header.getInstance(), user);
	}
}
