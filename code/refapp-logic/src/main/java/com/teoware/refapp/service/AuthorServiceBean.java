package com.teoware.refapp.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teoware.refapp.dao.AuthorDao;
import com.teoware.refapp.dao.DaoException;
import com.teoware.refapp.dao.dto.InsertAuthorRequest;
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
import com.teoware.refapp.service.interceptor.ValidationInterceptor;
import com.teoware.refapp.service.validation.Validate;
import com.teoware.refapp.service.validation.ValidationException;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AuthorServiceBean implements AuthorServiceLocal, AuthorServiceRemote {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(AuthorServiceBean.class);

	private static final String SERVICE_NAME = "Author service";
	private static final String DAO_EXCEPTION_MESSAGE = "DAO exception";

	@EJB
	private AuthorDao authorDao;

	@Interceptors({ ValidationInterceptor.class })
	@Validate(com.teoware.refapp.service.validation.group.RegisterAuthorRequestGroup.class)
	@Override
	public RegisterAuthorResponse registerAuthor(@Validate RegisterAuthorRequest request) throws ValidationException,
			ServiceException {
		LOG.info(SERVICE_NAME + ": Register author operation invoked.");

		Header header = request.getHeader();
		Author author = request.getBody();
		AuthorPassword authorPassword = request.getAuthorPassword();
		InsertAuthorRequest insertRequest = new InsertAuthorRequest(author, authorPassword);
		try {
			authorDao.insertAuthor(insertRequest);
		} catch (DaoException e) {
			LOG.error(SERVICE_NAME + ": Register author operation failed.");
			throw new ServiceException(DAO_EXCEPTION_MESSAGE, e);
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
