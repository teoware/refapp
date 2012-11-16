package com.teoware.refapp.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.teoware.refapp.service.dto.CreateNoteRequest;
import com.teoware.refapp.service.dto.CreateNoteResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class NoteServiceBean implements NoteService {

	private static final long serialVersionUID = 1L;

	@Override
	public CreateNoteResponse createNote(CreateNoteRequest request) {
		// TODO
		return null;
	}
}
