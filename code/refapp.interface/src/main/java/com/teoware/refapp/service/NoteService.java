package com.teoware.refapp.service;

import java.io.Serializable;

import com.teoware.refapp.dto.NoteCreateRequest;
import com.teoware.refapp.dto.NoteCreateResponse;

public interface NoteService extends Serializable {

	public NoteCreateResponse createNote(NoteCreateRequest noteCreateRequest);
}
