package com.teoware.refapp.service.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.teoware.refapp.service.NoteService;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.ChangeNoteRequest;
import com.teoware.refapp.service.dto.ChangeNoteResponse;
import com.teoware.refapp.service.dto.CreateNoteRequest;
import com.teoware.refapp.service.dto.CreateNoteResponse;
import com.teoware.refapp.service.dto.DeleteNoteRequest;
import com.teoware.refapp.service.dto.DeleteNoteResponse;
import com.teoware.refapp.service.dto.FindNoteRequest;
import com.teoware.refapp.service.dto.FindNoteResponse;
import com.teoware.refapp.service.dto.ListNotesRequest;
import com.teoware.refapp.service.dto.ListNotesResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class NoteServiceFacadeBean implements NoteServiceFacade {

    private static final long serialVersionUID = 1L;

    @Inject
    private NoteService service;

    @Override
    public CreateNoteResponse createNote(CreateNoteRequest request)
            throws ServiceException {
        return service.createNote(request);
    }

    @Override
    public FindNoteResponse findNote(FindNoteRequest request)
            throws ServiceException {
        return service.findNote(request);
    }

    @Override
    public ListNotesResponse listNotes(ListNotesRequest request)
            throws ServiceException {
        return service.listNotes(request);
    }

    @Override
    public ChangeNoteResponse changeNote(ChangeNoteRequest request)
            throws ServiceException {
        return service.changeNote(request);
    }

    @Override
    public DeleteNoteResponse deleteNote(DeleteNoteRequest request)
            throws ServiceException {
        return service.deleteNote(request);
    }
}
