package com.teoware.refapp.dao.rowmapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import com.teoware.refapp.dao.test.TestResultSetFactory;
import com.teoware.refapp.dao.util.ResultSetExtractor;
import com.teoware.refapp.dao.util.RowMapperResultSetExtractor;
import com.teoware.refapp.model.author.Author;

public class RowMapperResultSetExtractorTest {

	@Test
	public void testExtractDataFromAuthorResultSet() throws SQLException, ParseException {
		ResultSetExtractor<List<Author>> resultSetExtractor = new RowMapperResultSetExtractor<Author>(
				new AuthorRowMapper(), 0);
		ResultSet resultSet = TestResultSetFactory.createSelectAllAuthorsResultSet();
		List<Author> authorList = resultSetExtractor.extractData(resultSet);

		assertNotNull(authorList);
		assertTrue(authorList.size() == 3);

		Author author = authorList.get(0);

		assertNotNull(author);
		assertNotNull(author.getAuthorId());
		assertNotNull(author.getAuthorInfo());
		assertNotNull(author.getAuthorAddress());

		assertEquals("john.doe", author.getAuthorId().getUserName());
		assertEquals("John", author.getAuthorInfo().getFirstName());
		assertEquals("Storgata 1", author.getAuthorAddress().getAddress());
	}
}
