package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Uuid;

public class ReadNoteInput {

    private Uuid uuid;

    public ReadNoteInput() {
    }

    public ReadNoteInput(Uuid uuid) {
        this.uuid = uuid;
    }

    public Uuid getUuid() {
        return uuid;
    }

    public void setUuid(Uuid uuid) {
        this.uuid = uuid;
    }
}
