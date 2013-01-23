package com.teoware.refapp.dao.dto;

import java.util.List;

import com.teoware.refapp.model.note.Note;

public class ReadNoteOutput {

	List<Note> noteList;

	public ReadNoteOutput(List<Note> noteList) {
		this.noteList = noteList;
	}

	public List<Note> getNoteList() {
		return noteList;
	}
}
