package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Title;

public class UpdateNoteInput extends ChangeInput {

	private Title title;

	public UpdateNoteInput() {
		super();
	}

	public UpdateNoteInput(Id id, Title title) {
		super(id);
		this.title = title;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
}
