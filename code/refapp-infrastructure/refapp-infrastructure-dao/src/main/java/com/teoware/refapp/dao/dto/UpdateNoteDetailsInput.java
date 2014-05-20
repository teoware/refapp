package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.note.NoteDetails;

public class UpdateNoteDetailsInput extends ChangeInput {

    private NoteDetails noteDetails;

    public UpdateNoteDetailsInput() {
        super();
    }

    public UpdateNoteDetailsInput(Id id, NoteDetails noteDetails) {
        super(id);
        this.noteDetails = noteDetails;
    }

    public NoteDetails getNoteDetails() {
        return noteDetails;
    }

    public void setNoteDetails(NoteDetails noteDetails) {
        this.noteDetails = noteDetails;
    }
}
