package com.teoware.refapp.model.note;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.teoware.refapp.model.base.BaseBody;
import com.teoware.refapp.model.common.Uuid;

public class Note extends BaseBody {

    @NotNull
    @Valid
    private Uuid uuid;

    @NotNull
    @Valid
    private NoteDetails noteDetails;

    private NoteStatus noteStatus;

    public Note() {
    }

    public Note(Uuid uuid, NoteDetails noteDetails, NoteStatus noteStatus) {
        this.uuid = uuid;
        this.noteDetails = noteDetails;
        this.noteStatus = noteStatus;
    }

    public Uuid getUuid() {
        return uuid;
    }

    public void setUuid(Uuid uuid) {
        this.uuid = uuid;
    }

    public NoteDetails getNoteDetails() {
        return noteDetails;
    }

    public void setNoteDetails(NoteDetails noteDetails) {
        this.noteDetails = noteDetails;
    }

    public NoteStatus getNoteStatus() {
        return noteStatus;
    }

    public void setNoteStatus(NoteStatus noteStatus) {
        this.noteStatus = noteStatus;
    }
}
