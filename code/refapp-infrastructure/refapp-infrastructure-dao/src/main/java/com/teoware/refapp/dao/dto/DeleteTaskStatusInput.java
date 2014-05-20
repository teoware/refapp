package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class DeleteTaskStatusInput extends ChangeInput {

    public DeleteTaskStatusInput() {
        super();
    }

    public DeleteTaskStatusInput(Id id) {
        super(id);
    }
}
