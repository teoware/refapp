package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Username;

public class ReadNoteInput {

	private Username username;

	public ReadNoteInput() {
	}

	public ReadNoteInput(Username username) {
		this.username = username;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}
}
