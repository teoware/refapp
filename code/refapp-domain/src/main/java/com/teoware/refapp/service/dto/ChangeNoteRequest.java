package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.note.Note;

public class ChangeNoteRequest extends BaseMessage<Header, Note> {

	public ChangeNoteRequest(Header header, Note body) {
		super(header, body);
	}
}
