package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Title;

public class CreateTaskInput {

	private Id userId;
	private Title title;

	public CreateTaskInput() {
	}

	public CreateTaskInput(Id userId, Title title) {
		this.setUserId(userId);
		this.title = title;
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}
}
