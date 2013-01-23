package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Title;
import com.teoware.refapp.model.common.Username;

public class CreateTaskInput {

	private Username username;
	private Title title;

	public CreateTaskInput() {
	}

	public CreateTaskInput(Username username, Title title) {
		this.setUsername(username);
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
