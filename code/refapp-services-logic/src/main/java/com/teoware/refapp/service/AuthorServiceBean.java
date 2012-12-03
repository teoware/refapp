package com.teoware.refapp.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.dao.AuthorDao;
import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.dto.InsertAuthorRequest;
import com.teoware.refapp.dao.dto.SelectAuthorResponse;
import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.common.OperationResult;
import com.teoware.refapp.model.enums.Result;
import com.teoware.refapp.service.dto.FindAuthorRequest;
import com.teoware.refapp.service.dto.FindAuthorResponse;
import com.teoware.refapp.service.dto.ListAuthorsResponse;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.dto.RegisterAuthorResponse;

@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class AuthorServiceBean implements AuthorService {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AuthorServiceBean.class);

	private static final String SERVICE_NAME = "Author service";
	private static final String DAO_EXCEPTION_MESSAGE = "DAO exception";

	@Inject
	private AuthorDao dao;

	@Override
	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest request) throws ServiceException {
		LOG.info(SERVICE_NAME + ": Register author operation invoked.");

		try {
			Header header = request.getHeader();
			Author author = request.getBody();
			AuthorPassword authorPassword = request.getAuthorPassword();
			InsertAuthorRequest insertRequest = new InsertAuthorRequest(author, authorPassword);
			
			dao.insertAuthor(insertRequest);
			
			return new RegisterAuthorResponse(header, new OperationResult(Result.SUCCESS, null));
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Register author operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		}
	}

	@Override
	public FindAuthorResponse findAuthor(FindAuthorRequest request) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListAuthorsResponse listAuthors() throws ServiceException {
		LOG.info(SERVICE_NAME + ": List authors operation invoked.");

		try {
			SelectAuthorResponse selectResponse = dao.selectAllAuthors();

			return new ListAuthorsResponse(selectResponse.getAuthorList());
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": List authors operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
		}
	}
}
