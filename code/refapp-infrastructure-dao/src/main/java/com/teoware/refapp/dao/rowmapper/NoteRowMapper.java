package com.teoware.refapp.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.dao.metadata.NoteTables;
import com.teoware.refapp.dao.util.MapperHelper;
import com.teoware.refapp.model.note.Note;
import com.teoware.refapp.model.util.BeanFactory;

public class NoteRowMapper implements RowMapper<Note> {

    @Override
    public Note mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
        Note note = BeanFactory.createNote();

        note.getUuid().setUuid(result.getString(NoteTables.UUID_COLUMN_NAME));
        note.getNoteDetails().setTitle(result.getString(NoteTables.TITLE_COLUMN_NAME));
        note.getNoteDetails().setDescription(result.getString(NoteTables.DESCRIPTION_COLUMN_NAME));
        note.getNoteStatus().setStatus(MapperHelper.mapStatus(result.getString(NoteTables.STATUS_COLUMN_NAME)));
        note.getNoteStatus().setCreated(MapperHelper.mapTimestamp(result.getTimestamp(NoteTables.CREATED_COLUMN_NAME)));
        note.getNoteStatus().setModified(
                MapperHelper.mapTimestamp(result.getTimestamp(NoteTables.MODIFIED_COLUMN_NAME)));

        return note;
    }
}
