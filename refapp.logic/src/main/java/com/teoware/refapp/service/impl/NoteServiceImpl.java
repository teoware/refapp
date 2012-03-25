package com.teoware.refapp.service.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.teoware.refapp.dto.NoteCreateRequest;
import com.teoware.refapp.dto.NoteCreateResponse;
import com.teoware.refapp.service.NoteService;

@Stateless(mappedName = "NoteService")
@Remote(value = NoteService.class)
public class NoteServiceImpl implements NoteService {

	private static final long serialVersionUID = 1L;

	@Override
	public NoteCreateResponse createNote(NoteCreateRequest noteCreateRequest) {
		return null;
	}
}
