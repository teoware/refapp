package com.teoware.refapp.model.note;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.teoware.refapp.model.common.Title;

public class Note {

	@NotNull
	@Valid
	private Title title;

	@NotNull
	@Valid
	private NoteDetails noteDetails;

	private NoteStatus noteStatus;

	public Note() {
	}

	public Note(Title title, NoteDetails noteDetails, NoteStatus noteStatus) {
		this.title = title;
		this.noteDetails = noteDetails;
		this.noteStatus = noteStatus;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
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
