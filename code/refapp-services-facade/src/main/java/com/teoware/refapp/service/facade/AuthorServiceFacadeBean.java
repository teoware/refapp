package com.teoware.refapp.service.facade;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import com.teoware.refapp.service.AuthorService;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.FindAuthorRequest;
import com.teoware.refapp.service.dto.FindAuthorResponse;
import com.teoware.refapp.service.dto.ListAuthorsResponse;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.dto.RegisterAuthorResponse;
import com.teoware.refapp.service.validation.Validate;
import com.teoware.refapp.service.validation.ValidationException;
import com.teoware.refapp.service.validation.ValidationInterceptor;
import com.teoware.refapp.service.validation.group.RegisterAuthorRequestGroup;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AuthorServiceFacadeBean implements AuthorServiceFacade {

	private static final long serialVersionUID = 1L;

	@Inject
	private AuthorService service;

	@Interceptors({ ValidationInterceptor.class })
	@Validate(RegisterAuthorRequestGroup.class)
	@Override
	public RegisterAuthorResponse registerAuthor(@Validate RegisterAuthorRequest request) throws ValidationException,
			ServiceException {
		return service.registerAuthor(request);
	}

	@Override
	public FindAuthorResponse findAuthor(FindAuthorRequest request) throws ValidationException, ServiceException {
		return service.findAuthor(request);
	}

	@Override
	public ListAuthorsResponse listAuthors() throws ServiceException {
		return service.listAuthors();
	}

}
