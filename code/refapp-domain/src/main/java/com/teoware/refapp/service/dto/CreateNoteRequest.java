package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.common.Username;
import com.teoware.refapp.model.note.Note;

public class CreateNoteRequest extends BaseMessage<Header, Note> {

	private Username username;

	public CreateNoteRequest(Header header, Note body, Username username) {
		super(header, body);
		this.setUsername(username);
	}

	public CreateNoteRequest(Note body, Username username) {
		this(Header.getInstance(), body, username);
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
