package com.teoware.refapp.dao.rowmapper;

import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.util.BeanFactory;

public class AuthorRowMapper implements RowMapper<Author> {

	@Override
	public Author mapRow(ResultSet result, int rowCount) throws SQLException, ParseException {
		Author author = BeanFactory.createAuthorBean();
		
		author.getAuthorId().setUserName(result.getString(USERNAME_COLUMN_NAME));
		//author.getAuthorId().setPassword(result.getString(PASSWORD_COLUMN_NAME));
		
		return author;
	}
}
