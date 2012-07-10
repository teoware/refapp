package com.teoware.refapp.service.message;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.common.OperationResult;

public class RegisterAuthorResponse extends BaseMessage<Header, OperationResult> {

	public RegisterAuthorResponse(Header header, OperationResult body) {
		super(header, body);
	}
}
