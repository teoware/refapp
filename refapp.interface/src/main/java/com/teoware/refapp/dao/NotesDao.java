package com.teoware.refapp.dao;

import com.teoware.refapp.dto.NoteCreateRequest;
import com.teoware.refapp.dto.NoteCreateResponse;

public interface NotesDao {

	public NoteCreateResponse createNote(NoteCreateRequest noteCreateRequest);
}
