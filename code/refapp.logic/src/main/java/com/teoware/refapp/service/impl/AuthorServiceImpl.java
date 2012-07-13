package com.teoware.refapp.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.teoware.refapp.dao.AuthorDaoLocal;
import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.message.InsertAuthorRequest;
import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.common.OperationResult;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.service.AuthorServiceLocal;
import com.teoware.refapp.service.AuthorServiceRemote;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.ValidationException;
import com.teoware.refapp.service.message.FindAuthorRequest;
import com.teoware.refapp.service.message.FindAuthorResponse;
import com.teoware.refapp.service.message.ListAuthorsResponse;
import com.teoware.refapp.service.message.RegisterAuthorRequest;
import com.teoware.refapp.service.message.RegisterAuthorResponse;

@Stateless(mappedName="AuthorService")
public class AuthorServiceImpl implements AuthorServiceLocal, AuthorServiceRemote {

	private static final long serialVersionUID = 1L;

	@EJB
	protected AuthorDaoLocal authorDao;

	@Override
	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest request) throws ValidationException, ServiceException {
		Header header = request.getHeader();
		Author author = request.getBody();
		if (author.getAuthorId() == null) {
			throw new ValidationException();
		}
		InsertAuthorRequest insertRequest = new InsertAuthorRequest();
		try {
			authorDao.insertAuthor(insertRequest);
		} catch (DaoException e) {
			throw new ServiceException("DAO exception", e);
		}
		return new RegisterAuthorResponse(header, new OperationResult(Result.SUCCESS, null));
	}

	@Override
	public FindAuthorResponse findAuthor(FindAuthorRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListAuthorsResponse listAuthors() {
		// TODO Auto-generated method stub
		return null;
	}
}
