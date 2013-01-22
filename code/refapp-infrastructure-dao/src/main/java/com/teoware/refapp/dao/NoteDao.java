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
import com.teoware.refapp.dao.dto.ReadNoteInput;
import com.teoware.refapp.dao.dto.ReadNoteOutput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsInput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateNoteInput;
import com.teoware.refapp.dao.dto.UpdateNoteOutput;

@Local
public interface NoteDao extends Serializable {

	public CreateNoteOutput createNote(CreateNoteInput input);

	public CreateNoteDetailsOutput createNoteDetails(CreateNoteDetailsInput input);
	
	public ReadNoteOutput readNote(ReadNoteInput input);

	public UpdateNoteOutput updateNote(UpdateNoteInput input);

	public UpdateNoteDetailsOutput updateNoteDetails(UpdateNoteDetailsInput input);

	public DeleteNoteOutput deleteNote(DeleteNoteInput input);

	public DeleteNoteDetailsOutput deleteNoteDetails(DeleteNoteDetailsInput input);

	public void persistConnection();

	public void terminateConnection();
}
