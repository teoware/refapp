package com.teoware.refapp.dao;

import java.io.Serializable;

import com.teoware.refapp.dto.NoteCreateRequest;
import com.teoware.refapp.dto.NoteCreateResponse;

public interface NoteDao extends Serializable {

	public NoteCreateResponse createNote(NoteCreateRequest noteCreateRequest);
}
