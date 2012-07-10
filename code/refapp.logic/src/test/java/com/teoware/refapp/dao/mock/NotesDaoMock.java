package com.teoware.refapp.dao.mock;

import com.teoware.refapp.service.NoteServiceLocal;
import com.teoware.refapp.service.message.CreateNoteRequest;
import com.teoware.refapp.service.message.CreateNoteResponse;

public class NotesDaoMock implements NoteServiceLocal {

	private static final long serialVersionUID = 1L;

	@Override
	public CreateNoteResponse createNote(CreateNoteRequest noteCreateRequest) {
		return null;
	}
}
