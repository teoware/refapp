package com.teoware.refapp.dao;

import java.io.Serializable;

import javax.ejb.Local;

import com.teoware.refapp.dao.dto.CreateNoteInput;
import com.teoware.refapp.dao.dto.CreateNoteOutput;

@Local
public interface NoteDao extends Serializable {

	public CreateNoteOutput createNote(CreateNoteInput input);
}
