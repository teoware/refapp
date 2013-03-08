package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.common.Uuid;

public class DeleteNoteRequest extends BaseMessage<Header, Uuid> {

	public DeleteNoteRequest(Header header, Uuid uuid) {
		super(header, uuid);
	}

	public DeleteNoteRequest(Uuid uuid) {
		this(Header.getInstance(), uuid);
	}
}
