package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Uuid;

public class ReadTaskInput {

	private Uuid uuid;

	public ReadTaskInput() {
	}

	public ReadTaskInput(Uuid uuid) {
		this.setUuid(uuid);
	}

	public Uuid getUuid() {
		return uuid;
	}

	public void setUuid(Uuid uuid) {
		this.uuid = uuid;
	}
}
