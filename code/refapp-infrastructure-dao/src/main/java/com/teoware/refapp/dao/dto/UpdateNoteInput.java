package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Uuid;

public class UpdateNoteInput extends ChangeInput {

    private Uuid uuid;

    public UpdateNoteInput() {
        super();
    }

    public UpdateNoteInput(Id id, Uuid uuid) {
        super(id);
        this.uuid = uuid;
    }

    public Uuid getUuid() {
        return uuid;
    }

    public void setUuid(Uuid uuid) {
        this.uuid = uuid;
    }
}
