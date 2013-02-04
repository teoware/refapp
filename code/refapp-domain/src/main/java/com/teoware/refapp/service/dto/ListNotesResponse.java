package com.teoware.refapp.service.dto;

import java.util.List;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.note.Note;

public class ListNotesResponse {

	private Header header;
	private List<Note> noteList;

	public ListNotesResponse(Header header, List<Note> noteList) {
		this.header = header;
		this.noteList = noteList;
	}

	public Header getHeader() {
		return header;
	}

	public List<Note> getBody() {
		return noteList;
	}
}
