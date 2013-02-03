package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.note.NoteDetails;

public class CreateNoteDetailsInput extends ChangeInput {

	private NoteDetails noteDetails;

	public CreateNoteDetailsInput() {
		super();
	}

	public CreateNoteDetailsInput(Id id, NoteDetails noteDetails) {
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
