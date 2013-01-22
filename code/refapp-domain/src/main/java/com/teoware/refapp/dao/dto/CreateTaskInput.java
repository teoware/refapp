package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Title;

public class CreateTaskInput {

	private Title title;

	public CreateTaskInput() {
	}

	public CreateTaskInput(Title title) {
		this.title = title;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
}
