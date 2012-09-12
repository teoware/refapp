package com.teoware.refapp.dao;

import java.io.Serializable;

import com.teoware.refapp.dao.dto.DeleteAuthorRequest;
import com.teoware.refapp.dao.dto.DeleteAuthorResponse;
import com.teoware.refapp.dao.dto.InsertAuthorRequest;
import com.teoware.refapp.dao.dto.InsertAuthorResponse;
import com.teoware.refapp.dao.dto.PurgeAuthorsRequest;
import com.teoware.refapp.dao.dto.PurgeAuthorsResponse;
import com.teoware.refapp.dao.dto.SelectAuthorPasswordRequest;
import com.teoware.refapp.dao.dto.SelectAuthorPasswordResponse;
import com.teoware.refapp.dao.dto.SelectAuthorRequest;
import com.teoware.refapp.dao.dto.SelectAuthorResponse;
import com.teoware.refapp.dao.dto.UpdateAuthorRequest;
import com.teoware.refapp.dao.dto.UpdateAuthorResponse;

public interface AuthorDao extends Serializable {

	public InsertAuthorResponse insertAuthor(InsertAuthorRequest request) throws DaoException;

	public UpdateAuthorResponse updateAuthor(UpdateAuthorRequest request) throws DaoException;

	public SelectAuthorResponse selectAuthor(SelectAuthorRequest request) throws DaoException;

	public SelectAuthorResponse selectAuthor() throws DaoException;

	public SelectAuthorPasswordResponse selectAuthorPassword(SelectAuthorPasswordRequest request) throws DaoException;

	public DeleteAuthorResponse deleteAuthor(DeleteAuthorRequest request) throws DaoException;

	public PurgeAuthorsResponse purgeAuthors(PurgeAuthorsRequest request) throws DaoException;
}
