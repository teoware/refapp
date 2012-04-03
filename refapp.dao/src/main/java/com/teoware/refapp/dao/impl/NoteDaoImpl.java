package com.teoware.refapp.dao.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.dao.NoteDaoLocal;
import com.teoware.refapp.dto.NoteCreateRequest;
import com.teoware.refapp.dto.NoteCreateResponse;

@Stateless(mappedName = "NoteDao")
public class NoteDaoImpl implements NoteDaoLocal {

	private static final long serialVersionUID = 1L;

	@Override
	public NoteCreateResponse createNote(NoteCreateRequest noteCreateRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
