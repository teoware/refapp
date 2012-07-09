package com.teoware.refapp.service.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.service.NoteServiceLocal;
import com.teoware.refapp.service.NoteServiceRemote;
import com.teoware.refapp.service.message.CreateNoteRequest;
import com.teoware.refapp.service.message.CreateNoteResponse;

@Stateless(mappedName="NoteService")
public class NoteServiceImpl implements NoteServiceLocal, NoteServiceRemote {

	private static final long serialVersionUID = 1L;

	@Override
	public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest) {
		return null;
	}
}
