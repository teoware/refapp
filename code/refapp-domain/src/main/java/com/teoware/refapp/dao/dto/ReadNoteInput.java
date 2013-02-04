package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Title;

public class ReadNoteInput {

	private Title title;

	public ReadNoteInput() {
	}

	public ReadNoteInput(Title title) {
		this.setTitle(title);
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
}
