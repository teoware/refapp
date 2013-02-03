package com.teoware.refapp.service.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.teoware.refapp.service.NoteService;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.CreateNoteRequest;
import com.teoware.refapp.service.dto.CreateNoteResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class NoteServiceFacadeBean implements NoteServiceFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	private NoteService noteService;

	@Override
	public CreateNoteResponse createNote(CreateNoteRequest request) throws ServiceException {
		return noteService.createNote(request);
	}

}
