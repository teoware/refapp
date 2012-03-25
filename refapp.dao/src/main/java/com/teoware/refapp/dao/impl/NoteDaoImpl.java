package com.teoware.refapp.dao.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.teoware.refapp.dao.NoteDao;
import com.teoware.refapp.dto.NoteCreateRequest;
import com.teoware.refapp.dto.NoteCreateResponse;

@Stateless(mappedName = "NoteDao")
@Remote(value = NoteDao.class)
public class NoteDaoImpl implements NoteDao {

	private static final long serialVersionUID = 1L;

	@Override
	public NoteCreateResponse createNote(NoteCreateRequest noteCreateRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
