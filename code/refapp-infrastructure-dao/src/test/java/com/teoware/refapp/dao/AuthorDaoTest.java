package com.teoware.refapp.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.dao.dto.DeleteAuthorRequest;
import com.teoware.refapp.dao.dto.InsertAuthorRequest;
import com.teoware.refapp.dao.dto.PurgeAuthorsRequest;
import com.teoware.refapp.dao.dto.SelectAuthorRequest;
import com.teoware.refapp.dao.dto.SelectAuthorResponse;
import com.teoware.refapp.dao.dto.UpdateAuthorRequest;
import com.teoware.refapp.dao.test.TestDataFactory;
import com.teoware.refapp.dao.test.TestResultSetFactory;

@Category(com.teoware.refapp.test.UnitTestGroup.class)
public class AuthorDaoTest {

	@InjectMocks
	private UserDao authorDao = new UserDaoBean();

	@Mock
	private DataSource dataSource;

	@Mock
	private Connection connection;

	@Mock
	private PreparedStatement statement;

	private InsertAuthorRequest insertRequest;
	private UpdateAuthorRequest updateRequest;
	private SelectAuthorRequest selectRequest;
	private DeleteAuthorRequest deleteRequest;
	private PurgeAuthorsRequest purgeRequest;

	@Before
	public void setUp() {
		initMocks(this);

		insertRequest = TestDataFactory.createInsertAuthorJohnRequest();
		updateRequest = TestDataFactory.createUpdateAuthorJohnRequest();
		selectRequest = TestDataFactory.createSelectAuthorJohnRequest();
		deleteRequest = TestDataFactory.createDeleteAuthorJohnRequest();
		purgeRequest = TestDataFactory.createPurgeAuthorsRequest();
	}

	@Test
	public void testInsertAuthorJohn() throws Exception {
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		authorDao.insertAuthor(insertRequest);

		verify(connection, times(3)).prepareStatement(anyString());
	}

	@Test(expected = DaoException.class)
	public void testInsertAuthorJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString());

		authorDao.insertAuthor(insertRequest);
	}

	@Test
	public void testInsertAuthorJohnWithoutAddress() throws Exception {
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		insertRequest.getAuthor().setAuthorAddress(null);
		authorDao.insertAuthor(insertRequest);

		verify(connection, times(2)).prepareStatement(anyString());
	}

	@Test
	public void testUpdateAuthorJohn() throws Exception {
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		authorDao.updateAuthor(updateRequest);

		verify(connection, times(4)).prepareStatement(anyString());
	}

	@Test(expected = DaoException.class)
	public void testUpdateAuthorJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString());

		authorDao.updateAuthor(updateRequest);
	}

	@Test
	public void testUpdateAuthorJohnWithoutPassword() throws Exception {
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		updateRequest.setAuthorPassword(null);
		authorDao.updateAuthor(updateRequest);

		verify(connection, times(3)).prepareStatement(anyString());
	}

	@Test
	public void testUpdateAuthorJohnWithoutAddress() throws Exception {
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		updateRequest.getAuthor().setAuthorAddress(null);
		authorDao.updateAuthor(updateRequest);

		verify(connection, times(3)).prepareStatement(anyString());
	}

	@Test
	public void testUpdateAuthorJohnWithoutAuthorId() throws Exception {
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		updateRequest.getAuthor().setAuthorId(null);
		authorDao.updateAuthor(updateRequest);

		verifyZeroInteractions(connection);
	}

	@Test
	public void testUpdateAuthorJohnWithoutInfo() throws Exception {
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		updateRequest.getAuthor().setAuthorInfo(null);
		authorDao.updateAuthor(updateRequest);

		verify(connection, times(3)).prepareStatement(anyString());
	}

	@Test
	public void testUpdateAuthorJohnWithoutPasswordAddressAndInfo() throws Exception {
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		updateRequest.setAuthorPassword(null);
		updateRequest.getAuthor().setAuthorAddress(null);
		updateRequest.getAuthor().setAuthorInfo(null);
		authorDao.updateAuthor(updateRequest);

		verify(connection, times(1)).prepareStatement(anyString());
	}

	@Test
	public void testSelectAuthorJohn() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createSelectAuthorJohnResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		SelectAuthorResponse response = authorDao.selectAuthor(selectRequest);

		verify(connection, times(1)).prepareStatement(anyString());

		assertEquals(1, response.getAuthorList().size());
	}

	@Test
	public void testSelectAllAuthors() throws Exception {
		ResultSet resultSet = TestResultSetFactory.createSelectAllAuthorsResultSet();

		when(statement.executeQuery()).thenReturn(resultSet);
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		SelectAuthorResponse response = authorDao.selectAllAuthors();

		verify(connection, times(1)).prepareStatement(anyString());

		assertEquals(3, response.getAuthorList().size());
	}

	@Test(expected = DaoException.class)
	public void testSelectAuthorJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString());

		authorDao.selectAuthor(selectRequest);
	}

	@Test
	public void testDeleteAuthorJohn() throws Exception {
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		authorDao.deleteAuthor(deleteRequest);

		verify(connection, times(1)).prepareStatement(anyString());
	}

	@Test(expected = DaoException.class)
	public void testDeleteAuthorJohnPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString());

		authorDao.deleteAuthor(deleteRequest);
	}

	@Test
	public void testPurgeAuthors() throws Exception {
		when(connection.prepareStatement(anyString())).thenReturn(statement);

		authorDao.purgeAuthors(purgeRequest);

		verify(connection, times(1)).prepareStatement(anyString());
	}

	@Test(expected = DaoException.class)
	public void testPurgeAuthorsPrepareStatementThrowsDaoException() throws Exception {
		doThrow(SQLException.class).when(connection).prepareStatement(anyString());

		authorDao.purgeAuthors(purgeRequest);
	}
}
