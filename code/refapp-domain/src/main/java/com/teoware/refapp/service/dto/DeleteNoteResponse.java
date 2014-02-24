package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.common.OperationResult;

public class DeleteNoteResponse extends BaseMessage<Header, OperationResult> {

    public DeleteNoteResponse(Header header, OperationResult body) {
        super(header, body);
    }
}
