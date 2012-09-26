package com.teoware.refapp.service;

import javax.ejb.Stateless;

import com.teoware.refapp.service.NoteServiceLocal;
import com.teoware.refapp.service.NoteServiceRemote;
import com.teoware.refapp.service.dto.CreateNoteRequest;
import com.teoware.refapp.service.dto.CreateNoteResponse;

@Stateless(mappedName="NoteService")
public class NoteServiceBean implements NoteServiceLocal, NoteServiceRemote {

	private static final long serialVersionUID = 1L;

	@Override
	public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest) {
		return null;
	}
}
