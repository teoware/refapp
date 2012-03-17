package com.teoware.refapp.dao.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.teoware.refapp.dao.NotesDao;
import com.teoware.refapp.dto.NoteCreateRequest;
import com.teoware.refapp.dto.NoteCreateResponse;

@Stateless(mappedName = "/ejb/")
@Remote(value = NotesDao.class)
public class NotesDaoImpl implements NotesDao {

	private static final long serialVersionUID = 1L;

	@Override
	public NoteCreateResponse createNote(NoteCreateRequest noteCreateRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
