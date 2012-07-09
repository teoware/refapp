package com.teoware.refapp.dao;

import java.io.Serializable;

import com.teoware.refapp.dao.message.InsertNoteRequest;
import com.teoware.refapp.dao.message.InsertNoteResponse;

public interface NoteDao extends Serializable {

	public InsertNoteResponse insertNote(InsertNoteRequest insertNoteRequest);
}
