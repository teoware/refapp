package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class DeleteUserDetailsInput extends ChangeInput {

	public DeleteUserDetailsInput() {
		super();
	}

	public DeleteUserDetailsInput(Id id) {
		super(id);
	}
}
