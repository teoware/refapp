package com.teoware.refapp.dao;

import java.io.Serializable;

import com.teoware.refapp.dao.dto.InsertNoteRequest;
import com.teoware.refapp.dao.dto.InsertNoteResponse;

public interface NoteDao extends Serializable {

	public InsertNoteResponse insertNote(InsertNoteRequest request);
}
