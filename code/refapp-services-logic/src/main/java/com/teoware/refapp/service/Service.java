package com.teoware.refapp.service;

import com.teoware.refapp.model.common.OperationResult;
import com.teoware.refapp.model.enums.Result;

public abstract class Service {

	protected OperationResult createOperationResult(Result result, int rowsAffected) {
		return new OperationResult(result, "<" + rowsAffected + "> rows affected");
	}
}
