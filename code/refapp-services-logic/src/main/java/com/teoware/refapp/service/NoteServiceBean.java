package com.teoware.refapp.service;

import java.util.List;

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
import com.teoware.refapp.dao.dto.DeleteNoteDetailsInput;
import com.teoware.refapp.dao.dto.DeleteNoteDetailsOutput;
import com.teoware.refapp.dao.dto.DeleteNoteInput;
import com.teoware.refapp.dao.dto.DeleteNoteOutput;
import com.teoware.refapp.dao.dto.DeleteNoteStatusInput;
import com.teoware.refapp.dao.dto.DeleteNoteStatusOutput;
import com.teoware.refapp.dao.dto.ReadNoteInput;
import com.teoware.refapp.dao.dto.ReadNoteOutput;
import com.teoware.refapp.dao.dto.ReadNotesInput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsInput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateNoteInput;
import com.teoware.refapp.dao.dto.UpdateNoteOutput;
import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Title;
import com.teoware.refapp.model.common.Username;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.model.note.Note;
import com.teoware.refapp.service.dto.ChangeNoteRequest;
import com.teoware.refapp.service.dto.ChangeNoteResponse;
import com.teoware.refapp.service.dto.CreateNoteRequest;
import com.teoware.refapp.service.dto.CreateNoteResponse;
import com.teoware.refapp.service.dto.DeleteNoteRequest;
import com.teoware.refapp.service.dto.DeleteNoteResponse;
import com.teoware.refapp.service.dto.FindNoteRequest;
import com.teoware.refapp.service.dto.FindNoteResponse;
import com.teoware.refapp.service.dto.ListNotesRequest;
import com.teoware.refapp.service.dto.ListNotesResponse;

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
		LOG.info(SERVICE_NAME + ": Create note operation invoked.");

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

	@Override
	public FindNoteResponse findNote(FindNoteRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Find note operation invoked.");

		try {
			Header header = request.getHeader();
			Title title = request.getBody();

			ReadNoteInput input = new ReadNoteInput(title);
			ReadNoteOutput output = noteDao.readNote(input);
			List<Note> noteList = output.getNoteList();

			// TODO Sanity check if more than one note found

			return new FindNoteResponse(header, noteList.get(0));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Find user operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			noteDao.terminateConnection();
		}
	}

	@Override
	public ListNotesResponse listNotes(ListNotesRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": List notes operation invoked.");

		try {
			Header header = request.getHeader();
			Username username = request.getBody();

			noteDao.persistConnection();

			Id userId = userDao.readUserId(username);

			ReadNotesInput readNotesInput = new ReadNotesInput(userId);
			ReadNoteOutput readNoteOutput = noteDao.readNotes(readNotesInput);

			return new ListNotesResponse(header, readNoteOutput.getNoteList());
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": List notes operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			userDao.terminateConnection();
		}
	}

	@Override
	public ChangeNoteResponse changeNote(ChangeNoteRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Change note operation invoked.");

		try {
			int rowsAffected = 0;

			Header header = request.getHeader();
			Note note = request.getBody();

			noteDao.persistConnection();

			Id noteId = noteDao.readNoteId(note.getTitle());

			UpdateNoteInput updateNoteInput = new UpdateNoteInput(noteId, note.getTitle());
			UpdateNoteOutput updateNoteOutput = noteDao.updateNote(updateNoteInput);
			rowsAffected += updateNoteOutput.getRowsAffected();

			UpdateNoteDetailsInput updateNoteDetailsInput = new UpdateNoteDetailsInput(noteId, note.getNoteDetails());
			UpdateNoteDetailsOutput updateNoteDetailsOutput = noteDao.updateNoteDetails(updateNoteDetailsInput);
			rowsAffected += updateNoteDetailsOutput.getRowsAffected();

			return new ChangeNoteResponse(header, createOperationResult(Result.SUCCESS, rowsAffected));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Change note operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			noteDao.terminateConnection();
		}
	}

	@Override
	public DeleteNoteResponse deleteNote(DeleteNoteRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Delete user operation invoked.");

		try {
			int rowsAffected = 0;

			Header header = request.getHeader();
			Title title = request.getBody();

			noteDao.persistConnection();

			Id noteId = noteDao.readNoteId(title);

			DeleteNoteStatusInput deleteNoteStatusInput = new DeleteNoteStatusInput(noteId);
			DeleteNoteStatusOutput deleteNoteStatusOutput = noteDao.deleteNoteStatus(deleteNoteStatusInput);
			rowsAffected += deleteNoteStatusOutput.getRowsAffected();

			DeleteNoteDetailsInput deleteUserDetailsInput = new DeleteNoteDetailsInput(noteId);
			DeleteNoteDetailsOutput deleteUserDetailsOutput = noteDao.deleteNoteDetails(deleteUserDetailsInput);
			rowsAffected += deleteUserDetailsOutput.getRowsAffected();

			DeleteNoteInput deleteNoteInput = new DeleteNoteInput(noteId);
			DeleteNoteOutput deleteNoteOutput = noteDao.deleteNote(deleteNoteInput);
			rowsAffected += deleteNoteOutput.getRowsAffected();

			return new DeleteNoteResponse(header, createOperationResult(Result.SUCCESS, rowsAffected));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Delete user operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		} finally {
			noteDao.terminateConnection();
		}
	}
}
