package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;

public class DeleteNoteInput extends ChangeInput {

    public DeleteNoteInput() {
        super();
    }

    public DeleteNoteInput(Id id) {
        super(id);
    }
}
