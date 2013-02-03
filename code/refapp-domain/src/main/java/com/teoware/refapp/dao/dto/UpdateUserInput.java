package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Username;

public class UpdateUserInput extends ChangeInput {

	private Username username;

	public UpdateUserInput() {
		super();
	}

	public UpdateUserInput(Id id, Username username) {
		super(id);
		this.username = username;
	}

	public Username getUsername() {
		return username;
	}

	public void setUser(Username username) {
		this.username = username;
	}
}
