package com.teoware.refapp.service.mock;

import com.teoware.refapp.dto.NoteCreateRequest;
import com.teoware.refapp.dto.NoteCreateResponse;
import com.teoware.refapp.service.NoteService;

public class NotesServiceMock implements NoteService {

	private static final long serialVersionUID = 1L;

	@Override
	public NoteCreateResponse createNote(NoteCreateRequest noteCreateRequest) {
		return null;
	}
}
