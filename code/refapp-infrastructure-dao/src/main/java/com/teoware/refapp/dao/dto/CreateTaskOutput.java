package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class CreateTaskOutput extends CreateOutput {

    public CreateTaskOutput() {
        super();
    }

    public CreateTaskOutput(Id id, int rowsAffected) {
        super(id, rowsAffected);
    }
}
