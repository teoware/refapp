package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class DeleteTaskDetailsInput extends ChangeInput {

	public DeleteTaskDetailsInput() {
		super();
	}

	public DeleteTaskDetailsInput(Id id) {
		super(id);
	}
}
