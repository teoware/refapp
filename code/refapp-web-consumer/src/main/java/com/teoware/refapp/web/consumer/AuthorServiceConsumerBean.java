package com.teoware.refapp.web.consumer;

import javax.ejb.EJB;

import com.teoware.refapp.service.AuthorService;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.FindAuthorRequest;
import com.teoware.refapp.service.dto.FindAuthorResponse;
import com.teoware.refapp.service.dto.ListAuthorsResponse;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.dto.RegisterAuthorResponse;
import com.teoware.refapp.service.validation.ValidationException;

public class AuthorServiceConsumerBean implements AuthorServiceConsumer {

	@EJB
	private AuthorService authorService;

	@Override
	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest request) throws ValidationException,
			ServiceException {
		return authorService.registerAuthor(request);
	}

	@Override
	public FindAuthorResponse findAuthor(FindAuthorRequest request) throws ValidationException, ServiceException {
		return authorService.findAuthor(request);
	}

	@Override
	public ListAuthorsResponse listAuthors() throws ServiceException {
		return authorService.listAuthors();
	}
}
