package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class ReadUserPasswordInput {

    private Id userId;

    public ReadUserPasswordInput() {
    }

    public ReadUserPasswordInput(Id userId) {
        this.userId = userId;
    }

    public Id getUserId() {
        return userId;
    }

    public void setUserId(Id userId) {
        this.userId = userId;
    }
}
