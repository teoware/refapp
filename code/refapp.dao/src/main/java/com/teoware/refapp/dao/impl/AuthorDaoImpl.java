package com.teoware.refapp.dao.impl;

import static com.teoware.refapp.dao.metadata.AuthorTableMetaData.AUTHOR_VIEW_NAME;
import static com.teoware.refapp.dao.metadata.SchemaMetaData.SCHEMA_NAME;

import java.util.List;

import javax.ejb.Stateless;

import com.teoware.refapp.dao.AuthorDaoLocal;
import com.teoware.refapp.dao.BaseDao;
import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.message.DeleteAuthorRequest;
import com.teoware.refapp.dao.message.DeleteAuthorResponse;
import com.teoware.refapp.dao.message.InsertAuthorRequest;
import com.teoware.refapp.dao.message.InsertAuthorResponse;
import com.teoware.refapp.dao.message.SelectAuthorRequest;
import com.teoware.refapp.dao.message.SelectAuthorResponse;
import com.teoware.refapp.dao.message.UpdateAuthorRequest;
import com.teoware.refapp.dao.message.UpdateAuthorResponse;
import com.teoware.refapp.dao.rowmapper.AuthorRowMapper;
import com.teoware.refapp.dao.sql.SqlStatement;
import com.teoware.refapp.model.author.Author;

@Stateless(mappedName = "AuthorDao")
public class AuthorDaoImpl extends BaseDao<Author> implements AuthorDaoLocal {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = SCHEMA_NAME + "." + AUTHOR_VIEW_NAME;
	
	private AuthorRowMapper rowMapper = new AuthorRowMapper();

	@Override
	public InsertAuthorResponse insertAuthor(InsertAuthorRequest request) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateAuthorResponse updateAuthor(UpdateAuthorRequest request) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SelectAuthorResponse selectAuthor(SelectAuthorRequest request) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SelectAuthorResponse selectAuthor() throws DaoException {
		final SqlStatement sql = new SqlStatement("SELECT * FROM " + VIEW);
		List<Author> authorList = super.select(sql, rowMapper);
		return new SelectAuthorResponse(authorList);
	}

	@Override
	public DeleteAuthorResponse deleteAuthor(DeleteAuthorRequest request) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}
