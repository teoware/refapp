package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.common.Username;

public class SuspendUserRequest extends BaseMessage<Header, Username> {

    public SuspendUserRequest(Header header, Username userName) {
        super(header, userName);
    }

    public SuspendUserRequest(Username userName) {
        this(Header.getInstance(), userName);
    }
}
