package com.teoware.refapp.model.common;

import com.teoware.refapp.model.base.BaseBody;
import com.teoware.refapp.model.enums.Result;

public class OperationResult extends BaseBody {

	private Result result;
	private String message;

	public OperationResult(Result result, String message) {
		this.result = result;
		this.message = message;
	}

	public Result getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}
}
