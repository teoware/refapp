package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class DeleteTaskInput extends ChangeInput {

    public DeleteTaskInput() {
        super();
    }

    public DeleteTaskInput(Id id) {
        super(id);
    }
}
