package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public abstract class ChangeInput {

    protected Id id;

    protected ChangeInput() {
    }

    protected ChangeInput(Id id) {
        this.id = id;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }
}
