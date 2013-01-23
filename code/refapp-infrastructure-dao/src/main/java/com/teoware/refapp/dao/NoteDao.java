package com.teoware.refapp.dao;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.dao.dto.CreateNoteDetailsInput;
import com.teoware.refapp.dao.dto.CreateNoteDetailsOutput;
import com.teoware.refapp.dao.dto.CreateNoteInput;
import com.teoware.refapp.dao.dto.CreateNoteOutput;
import com.teoware.refapp.dao.dto.DeleteNoteDetailsInput;
import com.teoware.refapp.dao.dto.DeleteNoteDetailsOutput;
import com.teoware.refapp.dao.dto.DeleteNoteInput;
import com.teoware.refapp.dao.dto.DeleteNoteOutput;
import com.teoware.refapp.dao.dto.DeleteNoteStatusInput;
import com.teoware.refapp.dao.dto.DeleteNoteStatusOutput;
import com.teoware.refapp.dao.dto.Id;
import com.teoware.refapp.dao.dto.ReadNoteInput;
import com.teoware.refapp.dao.dto.ReadNoteOutput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsInput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateNoteInput;
import com.teoware.refapp.dao.dto.UpdateNoteOutput;
import com.teoware.refapp.model.common.Username;

@Local
public interface NoteDao extends Serializable {

	public CreateNoteOutput createNote(CreateNoteInput input) throws DaoException;

	public CreateNoteDetailsOutput createNoteDetails(CreateNoteDetailsInput input) throws DaoException;

	public ReadNoteOutput readNote(ReadNoteInput input) throws DaoException;

	public UpdateNoteOutput updateNote(UpdateNoteInput input) throws DaoException;

	public UpdateNoteDetailsOutput updateNoteDetails(UpdateNoteDetailsInput input) throws DaoException;

	public DeleteNoteOutput deleteNote(DeleteNoteInput input) throws DaoException;

	public DeleteNoteDetailsOutput deleteNoteDetails(DeleteNoteDetailsInput input) throws DaoException;

	public DeleteNoteStatusOutput deleteNoteStatus(DeleteNoteStatusInput input) throws DaoException;

	public Id readUserId(Username username) throws DaoException;

	public void persistConnection();

	public void terminateConnection();
}
