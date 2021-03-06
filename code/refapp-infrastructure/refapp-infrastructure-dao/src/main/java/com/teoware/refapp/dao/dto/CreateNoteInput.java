package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Uuid;

public class CreateNoteInput {

    private Id userId;
    private Uuid uuid;

    public CreateNoteInput() {
    }

    public CreateNoteInput(Id userId, Uuid uuid) {
        this.setUserId(userId);
        this.setUuid(uuid);
    }

    public Id getUserId() {
        return userId;
    }

    public void setUserId(Id userId) {
        this.userId = userId;
    }

    public Uuid getUuid() {
        return uuid;
    }

    public void setUuid(Uuid uuid) {
        this.uuid = uuid;
    }
}
