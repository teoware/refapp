package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class DeleteUserPasswordInput extends ChangeInput {

	public DeleteUserPasswordInput() {
		super();
	}

	public DeleteUserPasswordInput(Id id) {
		super(id);
	}
}
