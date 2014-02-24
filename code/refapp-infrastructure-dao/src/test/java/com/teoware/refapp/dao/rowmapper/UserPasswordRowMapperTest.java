package com.teoware.refapp.dao.rowmapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.teoware.refapp.model.user.UserPassword;

public class UserPasswordRowMapperTest {

    @InjectMocks
    private UserPasswordRowMapper rowMapper;

    @Mock
    private ResultSet result;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testMapRow() throws SQLException, ParseException {
        String pwd = "pwd";
        String salt = "salt";
        when(result.getString(anyString())).thenReturn(pwd, salt);

        UserPassword userPwd = rowMapper.mapRow(result, 0);

        assertNotNull(userPwd);
        assertEquals(pwd, userPwd.getPassword());
        assertEquals(salt, userPwd.getSalt());

        verify(result, times(2)).getString(anyString());
    }

    @Test(expected = SQLException.class)
    public void testMapRowThrowsSqlException() throws SQLException, ParseException {
        when(result.getString(anyString())).thenThrow(new SQLException());

        rowMapper.mapRow(result, 0);

        verify(result).getString(anyString());
    }
}
