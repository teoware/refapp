package com.teoware.refapp.dao.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.dao.NoteDaoLocal;
import com.teoware.refapp.dao.message.InsertNoteRequest;
import com.teoware.refapp.dao.message.InsertNoteResponse;

@Stateless(mappedName = "NoteDao")
public class NoteDaoImpl implements NoteDaoLocal {

	private static final long serialVersionUID = 1L;

	@Override
	public InsertNoteResponse insertNote(InsertNoteRequest insertNoteRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
