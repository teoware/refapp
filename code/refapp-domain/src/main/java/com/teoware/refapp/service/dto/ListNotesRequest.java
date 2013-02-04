package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.common.Username;

public class ListNotesRequest extends BaseMessage<Header, Username> {

	public ListNotesRequest(Header header, Username username) {
		super(header, username);
	}

	public ListNotesRequest(Username username) {
		this(Header.getInstance(), username);
	}
}
