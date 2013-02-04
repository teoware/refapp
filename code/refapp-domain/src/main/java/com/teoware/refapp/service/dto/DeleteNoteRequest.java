package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.common.Title;

public class DeleteNoteRequest extends BaseMessage<Header, Title> {

	public DeleteNoteRequest(Header header, Title title) {
		super(header, title);
	}

	public DeleteNoteRequest(Title title) {
		this(Header.getInstance(), title);
	}
}
