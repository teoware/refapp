package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Title;
import com.teoware.refapp.model.common.Username;

public class CreateNoteInput {

	private Username username;
	private Title title;

	public CreateNoteInput() {
	}

	public CreateNoteInput(Username username, Title title) {
		this.username = username;
		this.title = title;
	}

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
}
