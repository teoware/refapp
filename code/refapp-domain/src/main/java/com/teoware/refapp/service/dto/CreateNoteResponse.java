package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.common.OperationResult;

public class CreateNoteResponse extends BaseMessage<Header, OperationResult> {

    public CreateNoteResponse(Header header, OperationResult body) {
        super(header, body);
    }
}
