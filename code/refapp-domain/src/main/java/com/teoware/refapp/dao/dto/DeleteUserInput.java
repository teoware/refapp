package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class DeleteUserInput extends ChangeInput {

	public DeleteUserInput() {
		super();
	}

	public DeleteUserInput(Id id) {
		super(id);
	}
}
