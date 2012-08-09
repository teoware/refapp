package com.teoware.refapp.dao.test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.teoware.refapp.dao.rowmapper.AuthorRowMapper;
import com.teoware.refapp.dao.test.util.ResultSetFactory;
import com.teoware.refapp.dao.util.ResultSetExtractor;
import com.teoware.refapp.dao.util.RowMapperResultSetExtractor;
import com.teoware.refapp.model.author.Author;

public class RowMapperResultSetExtractorTest {

	@BeforeClass
	public static void oneTimeSetUp() {
		
	}

	@AfterClass
	public static void oneTimeTearDown() {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExtractDataFromAuthorResultSet() throws SQLException, ParseException {
		ResultSetExtractor<List<Author>> resultSetExtractor = new RowMapperResultSetExtractor<Author>(new AuthorRowMapper(), 0);
		ResultSet resultSet = ResultSetFactory.createSelectAuthorResultSet();
		List<Author> authorList = resultSetExtractor.extractData(resultSet);
		
		assertNotNull(authorList);
		assertTrue(authorList.size() == 3);
		
		Author author = authorList.get(0);
		
		assertNotNull(author);
		assertNotNull(author.getAuthorId());
		assertNotNull(author.getAuthorInfo());
		assertNotNull(author.getAuthorAddress());
		
		assertTrue("john.doe".equals(author.getAuthorId().getUserName()));
		assertTrue("John".equals(author.getAuthorInfo().getFirstName()));
		assertTrue("Storgata 1".equals(author.getAuthorAddress().getAddress()));
	}
}
