package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class ReadTasksInput {

	private Id userId;

	public ReadTasksInput() {
	}

	public ReadTasksInput(Id userId) {
		this.setUserId(userId);
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}
}
