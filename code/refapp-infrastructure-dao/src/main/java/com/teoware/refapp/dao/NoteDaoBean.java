package com.teoware.refapp.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.teoware.refapp.dao.dto.InsertNoteRequest;
import com.teoware.refapp.dao.dto.InsertNoteResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class NoteDaoBean implements NoteDao {

	private static final long serialVersionUID = 1L;

	@Override
	public InsertNoteResponse insertNote(InsertNoteRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
