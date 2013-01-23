package com.teoware.refapp.dao.rowmapper;

import static com.teoware.refapp.dao.metadata.NoteTables.CREATED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.NoteTables.DESCRIPTION_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.NoteTables.MODIFIED_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.NoteTables.STATUS_COLUMN_NAME;
import static com.teoware.refapp.dao.metadata.NoteTables.TITLE_COLUMN_NAME;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.dao.util.MapperHelper;
import com.teoware.refapp.model.note.Note;
import com.teoware.refapp.model.util.BeanFactory;

public class NoteRowMapper implements RowMapper<Note> {

	@Override
	public Note mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		Note note = BeanFactory.createNote();

		note.getTitle().setTitle(result.getString(TITLE_COLUMN_NAME));
		note.getNoteDetails().setDescription(result.getString(DESCRIPTION_COLUMN_NAME));
		note.getNoteStatus().setStatus(MapperHelper.mapStatus(result.getString(STATUS_COLUMN_NAME)));
		note.getNoteStatus().setCreated(MapperHelper.mapTimestamp(result.getTimestamp(CREATED_COLUMN_NAME)));
		note.getNoteStatus().setModified(MapperHelper.mapTimestamp(result.getTimestamp(MODIFIED_COLUMN_NAME)));

		return note;
	}
}
