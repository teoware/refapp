package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.user.Username;

public class ActivateUserRequest extends BaseMessage<Header, Username> {

	public ActivateUserRequest(Header header, Username userName) {
		super(header, userName);
	}
	
	public ActivateUserRequest(Username userName) {
		this(Header.getInstance(), userName);
	}
}
