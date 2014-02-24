package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class DeleteNoteStatusInput extends ChangeInput {

    public DeleteNoteStatusInput() {
        super();
    }

    public DeleteNoteStatusInput(Id id) {
        super(id);
    }
}
