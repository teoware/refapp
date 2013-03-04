package com.teoware.refapp.service.facade;

import java.io.Serializable;

import javax.ejb.Local;

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

@Local
public interface NoteServiceFacade extends Serializable {

	public CreateNoteResponse createNote(CreateNoteRequest request) throws ServiceException;
	
	public FindNoteResponse findNote(FindNoteRequest request) throws ServiceException;

	public ListNotesResponse listNotes(ListNotesRequest request) throws ServiceException;

	public ChangeNoteResponse changeNote(ChangeNoteRequest request) throws ServiceException;

	public DeleteNoteResponse deleteNote(DeleteNoteRequest request)
			throws ServiceException;
}
