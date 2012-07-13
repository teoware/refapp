package com.teoware.refapp.model.common;

import com.teoware.refapp.model.base.BaseBody;
import com.teoware.refapp.model.enums.Result;

public class OperationResult extends BaseBody {

	private Result result;
	private String description;

	public OperationResult(Result result, String description) {
		this.result = result;
		this.description = description;
	}

	public Result getResult() {
		return result;
	}

	public String getDescription() {
		return description;
	}
}
