package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.user.User;

public class FindUserResponse extends BaseMessage<Header, User> {

	public FindUserResponse(Header header, User user) {
		super(header, user);
	}
}
