package com.teoware.refapp.service;

import java.io.Serializable;

import com.teoware.refapp.service.message.CreateNoteRequest;
import com.teoware.refapp.service.message.CreateNoteResponse;

public interface NoteService extends Serializable {

	public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest);
}
