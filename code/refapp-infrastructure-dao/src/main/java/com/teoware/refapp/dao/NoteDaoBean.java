package com.teoware.refapp.dao;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;

import com.teoware.refapp.dao.dto.CreateNoteDetailsInput;
import com.teoware.refapp.dao.dto.CreateNoteDetailsOutput;
import com.teoware.refapp.dao.dto.CreateNoteInput;
import com.teoware.refapp.dao.dto.CreateNoteOutput;
import com.teoware.refapp.dao.dto.DeleteNoteDetailsInput;
import com.teoware.refapp.dao.dto.DeleteNoteDetailsOutput;
import com.teoware.refapp.dao.dto.DeleteNoteInput;
import com.teoware.refapp.dao.dto.DeleteNoteOutput;
import com.teoware.refapp.dao.dto.ReadNoteInput;
import com.teoware.refapp.dao.dto.ReadNoteOutput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsInput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateNoteInput;
import com.teoware.refapp.dao.dto.UpdateNoteOutput;
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
	public CreateNoteOutput createNote(CreateNoteInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateNoteDetailsOutput createNoteDetails(CreateNoteDetailsInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadNoteOutput readNote(ReadNoteInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateNoteOutput updateNote(UpdateNoteInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateNoteDetailsOutput updateNoteDetails(UpdateNoteDetailsInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteNoteOutput deleteNote(DeleteNoteInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeleteNoteDetailsOutput deleteNoteDetails(DeleteNoteDetailsInput input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void persistConnection() {
		super.setPersistConnection(Boolean.TRUE);
	}

	@Override
	public void terminateConnection() {
		super.setPersistConnection(Boolean.FALSE);
		super.closeConnection();
	}
}
