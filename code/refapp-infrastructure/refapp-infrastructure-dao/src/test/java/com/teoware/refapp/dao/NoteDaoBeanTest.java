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
import com.teoware.refapp.dao.dto.ReadNoteInput;
import com.teoware.refapp.dao.dto.ReadNoteOutput;
import com.teoware.refapp.dao.dto.ReadNotesInput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsInput;
import com.teoware.refapp.dao.dto.UpdateNoteDetailsOutput;
import com.teoware.refapp.dao.dto.UpdateNoteInput;
import com.teoware.refapp.dao.dto.UpdateNoteOutput;
import com.teoware.refapp.dao.dto.UpdateNoteStatusInput;
import com.teoware.refapp.dao.dto.UpdateNoteStatusOutput;
import com.teoware.refapp.dao.test.TestDataFactory;
import com.teoware.refapp.dao.test.TestResultSetFactory;
import com.teoware.refapp.model.common.Id;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class NoteDaoBeanTest {

    @InjectMocks
    private NoteDaoBean dao;

    @Mock
    private DataSource dataSource;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement statement;

    @Mock
    private ResultSet resultSet;

    private CreateNoteInput createInput;
    private CreateNoteDetailsInput createDetailsInput;
    private ReadNoteInput readInput;
    private ReadNotesInput readAllInput;
    private UpdateNoteInput updateInput;
    private UpdateNoteDetailsInput updateDetailsInput;
    private UpdateNoteStatusInput updateStatusInput;
    private DeleteNoteInput deleteInput;
    private DeleteNoteDetailsInput deleteDetailsInput;
    private DeleteNoteStatusInput deleteStatusInput;

    @Before
    public void setUp() {
        initMocks(this);

        createInput = TestDataFactory.createCreateNoteInput1();
        createDetailsInput = TestDataFactory.createCreateNoteDetailsInput1();
        readInput = TestDataFactory.createReadNoteInput1();
        readAllInput = TestDataFactory.createReadNotesInput1();
        updateInput = TestDataFactory.createUpdateNoteInput1();
        updateDetailsInput = TestDataFactory.createUpdateNoteDetailsInput1();
        updateStatusInput = TestDataFactory.createUpdateNoteStatusInput1();
        deleteInput = TestDataFactory.createDeleteNoteInput();
        deleteDetailsInput = TestDataFactory.createDeleteNoteDetailsInput();
        deleteStatusInput = TestDataFactory.createDeleteNoteStatusInput();
    }

    @Test
    public void testCreateNote1() throws SQLException, DaoException {
        when(resultSet.getLong(anyString())).thenReturn(0L);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

        CreateNoteOutput output = dao.createNote(createInput);

        assertEquals(0, output.getRowsAffected());
        assertEquals(0, output.getId().getId().intValue());
        verify(connection).isClosed();
        verify(connection).prepareStatement(anyString(), anyInt());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test(expected = DaoException.class)
    public void testCreateNote1PrepareStatementThrowsDaoException() throws Exception {
        doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

        dao.createNote(createInput);
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testCreateNoteDetails1() throws Exception {
        when(resultSet.getLong(anyString())).thenReturn(0L);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

        CreateNoteDetailsOutput output = dao.createNoteDetails(createDetailsInput);

        assertEquals(0, output.getRowsAffected());
        verify(connection).isClosed();
        verify(connection).prepareStatement(anyString(), anyInt());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testReadNoteId1() throws Exception {
        resultSet = TestResultSetFactory.createReadNoteId1ResultSet();

        when(statement.executeQuery()).thenReturn(resultSet);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

        Id id = dao.readNoteId(readInput.getUuid());

        assertNotNull(id);
        verify(connection).isClosed();
        verify(connection).prepareStatement(anyString(), anyInt());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test(expected = DaoException.class)
    public void testReadNoteId1PrepareStatementThrowsDaoException() throws Exception {
        doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

        dao.readNoteId(readInput.getUuid());

        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testReadNote1() throws Exception {
        resultSet = TestResultSetFactory.createReadNote1ResultSet();

        when(statement.executeQuery()).thenReturn(resultSet);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

        ReadNoteOutput output = dao.readNote(readInput);

        assertEquals(1, output.getNoteList().size());
        verify(connection).isClosed();
        verify(connection).prepareStatement(anyString(), anyInt());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test(expected = DaoException.class)
    public void testReadNote1PrepareStatementThrowsDaoException() throws Exception {
        doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

        dao.readNote(readInput);
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testReadNotes() throws Exception {
        resultSet = TestResultSetFactory.createReadNote1ResultSet();

        when(statement.executeQuery()).thenReturn(resultSet);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

        ReadNoteOutput output = dao.readNotes(readAllInput);

        assertEquals(1, output.getNoteList().size());
        verify(connection).isClosed();
        verify(connection).prepareStatement(anyString(), anyInt());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test(expected = DaoException.class)
    public void testReadNotesPrepareStatementThrowsDaoException() throws Exception {
        doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

        dao.readNotes(readAllInput);
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testUpdateNote1() throws Exception {
        when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(statement.executeUpdate()).thenReturn(0);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

        UpdateNoteOutput output = dao.updateNote(updateInput);

        assertEquals(0, output.getRowsAffected());
        verify(connection).isClosed();
        verify(connection).prepareStatement(anyString(), anyInt());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testUpdateNoteNull() throws Exception {
        updateInput.setUuid(null);

        UpdateNoteOutput output = dao.updateNote(updateInput);

        assertEquals(0, output.getRowsAffected());
        verifyZeroInteractions(connection);
    }

    @Test(expected = DaoException.class)
    public void testUpdateNote1ThrowsDaoException() throws Exception {
        doThrow(SQLException.class).when(connection).prepareStatement(anyString(), anyInt());

        dao.updateNote(updateInput);
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testUpdateNoteDetails1() throws Exception {
        when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(statement.executeUpdate()).thenReturn(0);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

        UpdateNoteDetailsOutput output = dao.updateNoteDetails(updateDetailsInput);

        assertEquals(0, output.getRowsAffected());
        verify(connection).isClosed();
        verify(connection).prepareStatement(anyString(), anyInt());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testUpdateNoteDetailsNull() throws Exception {
        updateDetailsInput.setNoteDetails(null);

        UpdateNoteDetailsOutput output = dao.updateNoteDetails(updateDetailsInput);

        assertEquals(0, output.getRowsAffected());
        verifyZeroInteractions(connection);
    }

    @Test
    public void testUpdateNoteStatus1() throws Exception {
        when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(statement.executeUpdate()).thenReturn(0);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

        UpdateNoteStatusOutput output = dao.updateNoteStatus(updateStatusInput);

        assertEquals(0, output.getRowsAffected());
        verify(connection).isClosed();
        verify(connection).prepareStatement(anyString(), anyInt());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testUpdateNoteStatusNull() throws Exception {
        updateStatusInput.setNoteStatus(null);

        UpdateNoteStatusOutput output = dao.updateNoteStatus(updateStatusInput);

        assertEquals(0, output.getRowsAffected());
        verifyZeroInteractions(connection);
    }

    @Test
    public void testDeleteNote1() throws Exception {
        when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(statement.executeUpdate()).thenReturn(0);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

        DeleteNoteOutput output = dao.deleteNote(deleteInput);

        assertEquals(0, output.getRowsAffected());
        verify(connection).isClosed();
        verify(connection).prepareStatement(anyString(), anyInt());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testDeleteNoteDetails1() throws Exception {
        when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(statement.executeUpdate()).thenReturn(0);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

        DeleteNoteDetailsOutput output = dao.deleteNoteDetails(deleteDetailsInput);

        assertEquals(0, output.getRowsAffected());
        verify(connection).isClosed();
        verify(connection).prepareStatement(anyString(), anyInt());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testDeleteNoteStatus1() throws Exception {
        when(resultSet.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(statement.executeUpdate()).thenReturn(0);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);

        DeleteNoteStatusOutput output = dao.deleteNoteStatus(deleteStatusInput);

        assertEquals(0, output.getRowsAffected());
        verify(connection).isClosed();
        verify(connection).prepareStatement(anyString(), anyInt());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }

    @Test
    public void testPersistConnection() {
        dao.persistConnection();

        assertTrue(dao.isPersistConnection());
    }

    @Test
    public void testTerminateConnection() throws SQLException {
        dao.terminateConnection();

        assertFalse(dao.isPersistConnection());
        verify(connection).close();
        verifyNoMoreInteractions(connection);
    }
}
