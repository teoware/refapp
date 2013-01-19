package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.Username;

public class ChangeUserRequest extends BaseMessage<Header, User> {

	private Username username;

	public ChangeUserRequest(Header header, User user, Username username) {
		super(header, user);
	}

	public ChangeUserRequest(User user, Username username) {
		this(Header.getInstance(), user, username);
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
