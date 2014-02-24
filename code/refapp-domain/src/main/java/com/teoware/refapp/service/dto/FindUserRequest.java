package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.base.BaseMessage;
import com.teoware.refapp.model.common.Username;

public class FindUserRequest extends BaseMessage<Header, Username> {

    public FindUserRequest(Header header, Username username) {
        super(header, username);
    }

    public FindUserRequest(Username username) {
        this(Header.getInstance(), username);
    }
}
