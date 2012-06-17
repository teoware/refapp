package com.teoware.refapp.service.mock;

import com.teoware.refapp.dto.NoteCreateRequest;
import com.teoware.refapp.dto.NoteCreateResponse;
import com.teoware.refapp.service.NoteServiceLocal;

public class NotesServiceMock implements NoteServiceLocal {

	private static final long serialVersionUID = 1L;

	@Override
	public NoteCreateResponse createNote(NoteCreateRequest noteCreateRequest) {
		return null;
	}
}
