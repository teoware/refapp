package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class DeleteUserStatusInput extends ChangeInput {

    public DeleteUserStatusInput() {
        super();
    }

    public DeleteUserStatusInput(Id id) {
        super(id);
    }
}
