package com.teoware.refapp.service.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.dto.NoteCreateRequest;
import com.teoware.refapp.dto.NoteCreateResponse;
import com.teoware.refapp.service.NoteServiceLocal;
import com.teoware.refapp.service.NoteServiceRemote;

@Stateless(mappedName="NoteService")
public class NoteServiceImpl implements NoteServiceLocal, NoteServiceRemote {

	private static final long serialVersionUID = 1L;

	@Override
	public NoteCreateResponse createNote(NoteCreateRequest noteCreateRequest) {
		return null;
	}
}
