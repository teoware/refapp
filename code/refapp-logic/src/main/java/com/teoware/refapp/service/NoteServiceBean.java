package com.teoware.refapp.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.teoware.refapp.service.dto.CreateNoteRequest;
import com.teoware.refapp.service.dto.CreateNoteResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class NoteServiceBean implements NoteServiceLocal, NoteServiceRemote {

	private static final long serialVersionUID = 1L;

	@Override
	public CreateNoteResponse createNote(CreateNoteRequest createNoteRequest) {
		return null;
	}
}
