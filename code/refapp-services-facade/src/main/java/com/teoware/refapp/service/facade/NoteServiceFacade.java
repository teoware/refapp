package com.teoware.refapp.service.facade;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.service.dto.CreateNoteRequest;
import com.teoware.refapp.service.dto.CreateNoteResponse;

@Local
public interface NoteServiceFacade extends Serializable {

	public CreateNoteResponse createNote(CreateNoteRequest request);
}
