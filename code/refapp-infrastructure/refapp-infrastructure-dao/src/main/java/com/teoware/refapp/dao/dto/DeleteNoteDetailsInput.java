package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class DeleteNoteDetailsInput extends ChangeInput {

    public DeleteNoteDetailsInput() {
        super();
    }

    public DeleteNoteDetailsInput(Id id) {
        super(id);
    }
}
