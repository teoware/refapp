package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Uuid;

public class UpdateTaskInput extends ChangeInput {

	private Uuid uuid;

	public UpdateTaskInput() {
		super();
	}

	public UpdateTaskInput(Id id, Uuid uuid) {
		super(id);
		this.setUuid(uuid);
	}

	public Uuid getUuid() {
		return uuid;
	}

	public void setUuid(Uuid uuid) {
		this.uuid = uuid;
	}
}
