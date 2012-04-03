package com.teoware.refapp.service.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.dto.NoteCreateRequest;
import com.teoware.refapp.dto.NoteCreateResponse;
import com.teoware.refapp.service.NoteServiceLocal;
import com.teoware.refapp.service.NoteServiceRemote;

@Stateless(name="NoteService", mappedName="/ejb/")
public class NoteServiceImpl implements NoteServiceLocal, NoteServiceRemote {

	private static final long serialVersionUID = 1L;

	@Override
	public NoteCreateResponse createNote(NoteCreateRequest noteCreateRequest) {
		return null;
	}
}
