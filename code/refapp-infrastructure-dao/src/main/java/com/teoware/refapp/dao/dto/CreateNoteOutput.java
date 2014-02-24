package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class CreateNoteOutput extends CreateOutput {

    public CreateNoteOutput() {
        super();
    }

    public CreateNoteOutput(Id id, int rowsAffected) {
        super(id, rowsAffected);
    }
}
