package com.teoware.refapp.service;

import java.io.Serializable;

import com.teoware.refapp.service.dto.CreateNoteRequest;
import com.teoware.refapp.service.dto.CreateNoteResponse;

public interface NoteService extends Serializable {

	public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest);
}
