package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Title;

public class CreateNoteInput {

	private Title title;

	public CreateNoteInput() {
	}

	public CreateNoteInput(Title title) {
		this.title = title;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
}
