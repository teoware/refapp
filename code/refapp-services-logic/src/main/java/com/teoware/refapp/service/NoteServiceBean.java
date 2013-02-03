package com.teoware.refapp.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.NoteDao;
import com.teoware.refapp.dao.UserDao;
import com.teoware.refapp.dao.dto.CreateNoteDetailsInput;
import com.teoware.refapp.dao.dto.CreateNoteDetailsOutput;
import com.teoware.refapp.dao.dto.CreateNoteInput;
import com.teoware.refapp.dao.dto.CreateNoteOutput;
import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.model.note.Note;
import com.teoware.refapp.service.dto.CreateNoteRequest;
import com.teoware.refapp.service.dto.CreateNoteResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class NoteServiceBean extends Service implements NoteService {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(NoteServiceBean.class);

	private static final String SERVICE_NAME = "Note service";
	private static final String DAO_EXCEPTION_MESSAGE = "DAO exception";

	@Inject
	protected UserDao userDao;

	@Inject
	protected NoteDao noteDao;

	@Override
	public CreateNoteResponse createNote(CreateNoteRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Register user operation invoked.");

		try {
			int rowsAffected = 0;

			Header header = request.getHeader();
			Note note = request.getBody();

			noteDao.persistConnection();

			Id userId = userDao.readUserId(request.getUsername());

			CreateNoteInput createNoteInput = new CreateNoteInput(userId, note.getTitle());
			CreateNoteOutput createNoteOutput = noteDao.createNote(createNoteInput);
			rowsAffected += createNoteOutput.getRowsAffected();

			CreateNoteDetailsInput createNoteDetailsInput = new CreateNoteDetailsInput(userId, note.getNoteDetails());
			CreateNoteDetailsOutput createNoteDetailsOutput = noteDao.createNoteDetails(createNoteDetailsInput);
			rowsAffected += createNoteDetailsOutput.getRowsAffected();

			return new CreateNoteResponse(header, createOperationResult(Result.SUCCESS, rowsAffected));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Create note operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			noteDao.terminateConnection();
		}
	}
}
