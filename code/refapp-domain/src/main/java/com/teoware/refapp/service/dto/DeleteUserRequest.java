package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.user.Username;

public class DeleteUserRequest extends BaseMessage<Header, Username> {

	public DeleteUserRequest(Header header, Username userName) {
		super(header, userName);
	}
	
	public DeleteUserRequest(Username userName) {
		this(Header.getInstance(), userName);
	}
}
