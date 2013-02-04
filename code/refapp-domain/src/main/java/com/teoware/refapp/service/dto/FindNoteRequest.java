package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.common.Title;

public class FindNoteRequest extends BaseMessage<Header, Title> {

	public FindNoteRequest(Header header, Title title) {
		super(header, title);
	}

	public FindNoteRequest(Title title) {
		this(Header.getInstance(), title);
	}
}
