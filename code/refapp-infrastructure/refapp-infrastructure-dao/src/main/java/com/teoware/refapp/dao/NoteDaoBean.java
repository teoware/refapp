package com.teoware.refapp.dao;

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
import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.dao.dto.ReadNoteInput;
import com.teoware.refapp.dao.dto.ReadNoteOutput;
import com.teoware.refapp.dao.dto.ReadNotesInput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsInput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateNoteInput;
import com.teoware.refapp.dao.dto.UpdateNoteOutput;
import com.teoware.refapp.dao.dto.UpdateNoteStatusInput;
import com.teoware.refapp.dao.dto.UpdateNoteStatusOutput;
import com.teoware.refapp.dao.metadata.JNDI;
import com.teoware.refapp.dao.metadata.NoteTables;
import com.teoware.refapp.dao.metadata.Schema;
import com.teoware.refapp.dao.rowmapper.IdRowMapper;
import com.teoware.refapp.dao.rowmapper.NoteRowMapper;
import com.teoware.refapp.dao.util.ChangeResult;
import com.teoware.refapp.dao.util.DaoHelper;
import com.teoware.refapp.dao.util.SQL;
import com.teoware.refapp.model.common.Uuid;
import com.teoware.refapp.model.note.Note;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class NoteDaoBean extends Dao implements NoteDao {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(UserDaoBean.class);

    private static final String DAO_NAME = "Note DAO";

    public static final String NOTES_VIEW = Schema.REFAPP_SCHEMA_NAME + "." + NoteTables.NOTES_VIEW_NAME;
    public static final String NOTES_TABLE = Schema.REFAPP_SCHEMA_NAME + "." + NoteTables.NOTES_TABLE_NAME;
    public static final String NOTE_STATUS_TABLE = Schema.REFAPP_SCHEMA_NAME + "." + NoteTables.NOTE_STATUS_TABLE_NAME;
    public static final String NOTE_DETAILS_TABLE = Schema.REFAPP_SCHEMA_NAME + "."
            + NoteTables.NOTE_DETAILS_TABLE_NAME;

    @Resource(mappedName = JNDI.REFAPP_DATASOURCE)
    private DataSource dataSource;

    private final IdRowMapper idRowMapper = new IdRowMapper();
    private final NoteRowMapper noteRowMapper = new NoteRowMapper();

    @PostConstruct
    private void initialize() {
        super.initialize(dataSource);
    }

    @Override
    public CreateNoteOutput createNote(CreateNoteInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Create note operation invoked.");

        SQL sql = new SQL.Builder().doInsert(NOTES_TABLE)
                .columnValues(NoteTables.USER_ID_COLUMN_NAME, NoteTables.UUID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getUserId(), input.getUuid());
        ChangeResult changeResult = super.create(sql, parameters);
        int rowsAffected = changeResult.getRowsAffected();
        Id taskId = changeResult.getAutoGeneratedKey();
        return new CreateNoteOutput(taskId, rowsAffected);
    }

    @Override
    public CreateNoteDetailsOutput createNoteDetails(CreateNoteDetailsInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Create note details operation invoked.");

        SQL sql = new SQL.Builder()
                .doInsert(NOTE_DETAILS_TABLE)
                .columnValues(NoteTables.NOTE_ID_COLUMN_NAME, NoteTables.TITLE_COLUMN_NAME,
                        NoteTables.DESCRIPTION_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getId(), input.getNoteDetails().getTitle(), input
                .getNoteDetails().getDescription());
        ChangeResult changeResult = super.create(sql, parameters);
        return new CreateNoteDetailsOutput(changeResult.getRowsAffected());
    }

    @Override
    public Id readNoteId(Uuid uuid) throws DaoException {
        LOG.info(DAO_NAME + ": Read note ID operation invoked.");

        SQL sql = new SQL.Builder().doSelect(NoteTables.ID_COLUMN_NAME).from(NOTES_TABLE)
                .where(NoteTables.UUID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(uuid);
        return super.read(sql, idRowMapper, parameters).get(0);
    }

    @Override
    public ReadNoteOutput readNote(ReadNoteInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Read note operation invoked.");

        SQL sql = new SQL.Builder().doSelectAll().from(NOTES_VIEW).where(NoteTables.UUID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getUuid());
        List<Note> noteList = super.read(sql, noteRowMapper, parameters);
        return new ReadNoteOutput(noteList);
    }

    @Override
    public ReadNoteOutput readNotes(ReadNotesInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Read notes operation invoked.");

        SQL sql = new SQL.Builder().doSelectAll().from(NOTES_VIEW).where(NoteTables.USER_ID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getUserId());
        List<Note> noteList = super.read(sql, noteRowMapper, parameters);
        return new ReadNoteOutput(noteList);
    }

    @Override
    public UpdateNoteOutput updateNote(UpdateNoteInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Update note operation invoked.");

        if (input.getUuid() != null) {
            SQL sql = new SQL.Builder().doUpdate(NOTES_TABLE).setColumn(NoteTables.UUID_COLUMN_NAME)
                    .where(NoteTables.ID_COLUMN_NAME).build();
            Object[] parameters = DaoHelper.generateArray(input.getUuid(), input.getId());
            ChangeResult changeResult = super.update(sql, parameters);
            return new UpdateNoteOutput(changeResult.getRowsAffected());
        } else {
            return new UpdateNoteOutput(0);
        }

    }

    @Override
    public UpdateNoteDetailsOutput updateNoteDetails(UpdateNoteDetailsInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Update note details operation invoked.");

        if (input.getNoteDetails() != null) {
            SQL sql = new SQL.Builder().doUpdate(NOTE_DETAILS_TABLE).setColumn(NoteTables.DESCRIPTION_COLUMN_NAME)
                    .where(NoteTables.ID_COLUMN_NAME).build();
            Object[] parameters = DaoHelper.generateArray(input.getNoteDetails().getDescription(), input.getId());
            ChangeResult changeResult = super.update(sql, parameters);
            return new UpdateNoteDetailsOutput(changeResult.getRowsAffected());
        } else {
            return new UpdateNoteDetailsOutput(0);
        }
    }

    @Override
    public UpdateNoteStatusOutput updateNoteStatus(UpdateNoteStatusInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Update note status operation invoked.");

        if (input.getNoteStatus() != null) {
            SQL sql = new SQL.Builder().doUpdate(NOTE_STATUS_TABLE).setColumn(NoteTables.STATUS_COLUMN_NAME)
                    .where(NoteTables.ID_COLUMN_NAME).build();
            Object[] parameters = DaoHelper.generateArray(input.getNoteStatus().getStatus(), input.getId());
            ChangeResult changeResult = super.update(sql, parameters);
            return new UpdateNoteStatusOutput(changeResult.getRowsAffected());
        } else {
            return new UpdateNoteStatusOutput(0);
        }
    }

    @Override
    public DeleteNoteOutput deleteNote(DeleteNoteInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Delete note operation invoked.");

        SQL sql = new SQL.Builder().doDelete(NOTES_TABLE).where(NoteTables.ID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getId());
        ChangeResult changeResult = super.delete(sql, parameters);
        return new DeleteNoteOutput(changeResult.getRowsAffected());
    }

    @Override
    public DeleteNoteDetailsOutput deleteNoteDetails(DeleteNoteDetailsInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Delete note details operation invoked.");

        SQL sql = new SQL.Builder().doDelete(NOTE_DETAILS_TABLE).where(NoteTables.ID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getId());
        ChangeResult changeResult = super.delete(sql, parameters);
        return new DeleteNoteDetailsOutput(changeResult.getRowsAffected());
    }

    @Override
    public DeleteNoteStatusOutput deleteNoteStatus(DeleteNoteStatusInput input) throws DaoException {
        LOG.info(DAO_NAME + ": Delete note status operation invoked.");

        SQL sql = new SQL.Builder().doDelete(NOTE_STATUS_TABLE).where(NoteTables.ID_COLUMN_NAME).build();
        Object[] parameters = DaoHelper.generateArray(input.getId());
        ChangeResult changeResult = super.delete(sql, parameters);
        return new DeleteNoteStatusOutput(changeResult.getRowsAffected());
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
