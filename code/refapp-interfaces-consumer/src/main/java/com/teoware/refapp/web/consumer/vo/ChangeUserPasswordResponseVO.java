package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.common.OperationResult;

public class ChangeUserPasswordResponseVO {

	private OperationResult result;

	public ChangeUserPasswordResponseVO(OperationResult result) {
		this.result = result;
	}

	public OperationResult getResult() {
		return result;
	}

	public void setResult(OperationResult result) {
		this.result = result;
	}
}
