package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.note.NoteStatus;

public class UpdateNoteStatusInput extends ChangeInput {

	private NoteStatus noteStatus;

	public UpdateNoteStatusInput() {
		super();
	}

	public UpdateNoteStatusInput(Id id, NoteStatus noteStatus) {
		super(id);
		this.setNoteStatus(noteStatus);
	}

	public NoteStatus getNoteStatus() {
		return noteStatus;
	}

	public void setNoteStatus(NoteStatus noteStatus) {
		this.noteStatus = noteStatus;
	}
}
