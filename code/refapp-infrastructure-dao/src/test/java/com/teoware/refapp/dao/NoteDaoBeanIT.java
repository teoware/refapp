package com.teoware.refapp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.dao.dto.CreateNoteDetailsInput;
import com.teoware.refapp.dao.dto.CreateNoteDetailsOutput;
import com.teoware.refapp.dao.dto.CreateNoteInput;
import com.teoware.refapp.dao.dto.CreateNoteOutput;
import com.teoware.refapp.dao.dto.CreateUserInput;
import com.teoware.refapp.dao.dto.CreateUserOutput;
import com.teoware.refapp.dao.mock.NoteDaoMock;
import com.teoware.refapp.dao.mock.UserDaoMock;
import com.teoware.refapp.dao.test.TestDataSourceHandler;
import com.teoware.refapp.dao.util.SQL;
import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.common.Username;
import com.teoware.refapp.test.util.TestDataFactory;

public class NoteDaoBeanIT {

	private static Connection connection;
	private static NoteDaoMock noteDao;
	private static UserDaoMock userDao;
	private static Id userId;

	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		try {
			connection = TestDataSourceHandler.initializeDatabase();
			noteDao = new NoteDaoMock(connection);
			userDao = new UserDaoMock(connection);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		noteDao.closeAll();
		userDao.closeAll();
	}

	@Before
	public void setUp() throws Exception {
		cleanTables();
		createUsers();
	}

	@Test
	public void testCreateAndReadNote1() {
		try {
			CreateNoteInput createNoteInput = TestDataFactory.createCreateNoteInput1(userId);
			CreateNoteOutput createNoteOutput = noteDao.createNote(createNoteInput);

			assertNotNull(createNoteOutput);
			assertEquals(1, createNoteOutput.getRowsAffected());
			assertNotNull(createNoteOutput.getId());

			CreateNoteDetailsInput createNoteDetailsInput = TestDataFactory
					.createCreateNoteDetailsInput1(createNoteOutput.getId());
			CreateNoteDetailsOutput createNoteDetailsOutput = noteDao.createNoteDetails(createNoteDetailsInput);

			assertNotNull(createNoteDetailsOutput);
			assertEquals(1, createNoteDetailsOutput.getRowsAffected());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	private static void createUsers() throws DaoException {
		try {
			userId = userDao.readUserId(new Username("jonah"));

			if (userId == null) {
				CreateUserInput createUserInput = TestDataFactory.createCreateUserInputJonah();
				CreateUserOutput createUserOutput = userDao.createUser(createUserInput);
				userId = createUserOutput.getId();
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	private static void cleanTables() throws DaoException {
		try {
			noteDao.delete(new SQL("DELETE FROM " + NoteDaoBean.NOTE_STATUS_TABLE));
			noteDao.delete(new SQL("DELETE FROM " + NoteDaoBean.NOTE_DETAILS_TABLE));
			noteDao.delete(new SQL("DELETE FROM " + NoteDaoBean.NOTES_TABLE));
			userDao.delete(new SQL("DELETE FROM " + UserDaoBean.USERS_TABLE));
			userDao.delete(new SQL("DELETE FROM " + UserDaoBean.USER_STATUS_TABLE));
			userDao.delete(new SQL("DELETE FROM " + UserDaoBean.USER_ADDRESS_TABLE));
			userDao.delete(new SQL("DELETE FROM " + UserDaoBean.USER_PASSWORD_TABLE));
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
