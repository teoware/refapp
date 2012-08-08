package com.teoware.refapp.dao;

import java.io.Serializable;

import com.teoware.refapp.dao.message.DeleteAuthorRequest;
import com.teoware.refapp.dao.message.DeleteAuthorResponse;
import com.teoware.refapp.dao.message.InsertAuthorRequest;
import com.teoware.refapp.dao.message.InsertAuthorResponse;
import com.teoware.refapp.dao.message.PurgeAuthorsRequest;
import com.teoware.refapp.dao.message.PurgeAuthorsResponse;
import com.teoware.refapp.dao.message.SelectAuthorPasswordRequest;
import com.teoware.refapp.dao.message.SelectAuthorPasswordResponse;
import com.teoware.refapp.dao.message.SelectAuthorRequest;
import com.teoware.refapp.dao.message.SelectAuthorResponse;
import com.teoware.refapp.dao.message.UpdateAuthorRequest;
import com.teoware.refapp.dao.message.UpdateAuthorResponse;

public interface AuthorDao extends Serializable {

	public InsertAuthorResponse insertAuthor(InsertAuthorRequest request) throws DaoException;

	public UpdateAuthorResponse updateAuthor(UpdateAuthorRequest request) throws DaoException;

	public SelectAuthorResponse selectAuthor(SelectAuthorRequest request) throws DaoException;

	public SelectAuthorResponse selectAuthor() throws DaoException;

	public SelectAuthorPasswordResponse selectAuthorPassword(SelectAuthorPasswordRequest request) throws DaoException;

	public DeleteAuthorResponse deleteAuthor(DeleteAuthorRequest request) throws DaoException;

	public PurgeAuthorsResponse purgeAuthors(PurgeAuthorsRequest request) throws DaoException;
}
