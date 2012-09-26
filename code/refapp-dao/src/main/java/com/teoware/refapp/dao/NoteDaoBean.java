package com.teoware.refapp.dao;

import javax.ejb.Stateless;

import com.teoware.refapp.dao.dto.InsertNoteRequest;
import com.teoware.refapp.dao.dto.InsertNoteResponse;

@Stateless(mappedName = "NoteDao")
public class NoteDaoBean implements NoteDaoLocal {

	private static final long serialVersionUID = 1L;

	@Override
	public InsertNoteResponse insertNote(InsertNoteRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
