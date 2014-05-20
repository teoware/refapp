package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public abstract class CreateOutput extends ChangeOutput {

    protected Id id;

    protected CreateOutput() {
    }

    protected CreateOutput(Id id, int rowsAffected) {
        super(rowsAffected);
        this.id = id;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }
}
