package com.teoware.refapp.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.teoware.refapp.dao.dto.InsertNoteRequest;
import com.teoware.refapp.dao.dto.InsertNoteResponse;
import com.teoware.refapp.dao.metadata.JNDI;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class NoteDaoBean extends Dao implements NoteDao {

	private static final long serialVersionUID = 1L;

	@Resource(mappedName = JNDI.REFAPP_DATASOURCE)
	private DataSource dataSource;

	@PostConstruct
	private void initialize() {
		super.initialize(dataSource);
	}

	@Override
	public InsertNoteResponse insertNote(InsertNoteRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
}
