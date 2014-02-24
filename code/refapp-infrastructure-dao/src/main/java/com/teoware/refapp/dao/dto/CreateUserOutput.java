package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class CreateUserOutput extends CreateOutput {

    public CreateUserOutput() {
        super();
    }

    public CreateUserOutput(Id id, int rowsAffected) {
        super(id, rowsAffected);
    }
}
