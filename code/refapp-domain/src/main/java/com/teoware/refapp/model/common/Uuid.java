package com.teoware.refapp.model.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.teoware.refapp.model.base.BaseBody;
import com.teoware.refapp.model.util.ValidationRegex;

public class Uuid extends BaseBody {

	@NotNull
	@Size(min = 36, max = 36)
	@Pattern(regexp = ValidationRegex.UUID)
	private String uuid;

	public Uuid() {
	}

	public Uuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
