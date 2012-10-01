package com.teoware.refapp.dao;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.dao.dto.InsertNoteRequest;
import com.teoware.refapp.dao.dto.InsertNoteResponse;

@Local
public interface NoteDao extends Serializable {

	public InsertNoteResponse insertNote(InsertNoteRequest request);
}
