package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class ReadNotesInput {

	private Id userId;

	public ReadNotesInput() {
	}

	public ReadNotesInput(Id userId) {
		this.setUserId(userId);
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}
}
