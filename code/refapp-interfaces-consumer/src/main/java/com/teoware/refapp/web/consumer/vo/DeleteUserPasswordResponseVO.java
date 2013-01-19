package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.common.OperationResult;

public class DeleteUserPasswordResponseVO {

	private OperationResult result;

	public DeleteUserPasswordResponseVO(OperationResult result) {
		this.result = result;
	}

	public OperationResult getResult() {
		return result;
	}

	public void setResult(OperationResult result) {
		this.result = result;
	}
}
