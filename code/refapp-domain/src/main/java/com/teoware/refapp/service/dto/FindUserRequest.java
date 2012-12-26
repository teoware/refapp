package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.user.Username;

public class FindUserRequest extends BaseMessage<Header, Username> {

	public FindUserRequest(Header header, Username userName) {
		super(header, userName);
	}
	
	public FindUserRequest(Username userName) {
		this(Header.getInstance(), userName);
	}
}
