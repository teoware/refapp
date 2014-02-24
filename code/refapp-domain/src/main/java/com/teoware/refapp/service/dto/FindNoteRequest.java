package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.common.Uuid;

public class FindNoteRequest extends BaseMessage<Header, Uuid> {

    public FindNoteRequest(Header header, Uuid uuid) {
        super(header, uuid);
    }

    public FindNoteRequest(Uuid uuid) {
        this(Header.getInstance(), uuid);
    }
}
