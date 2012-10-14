package com.teoware.refapp.web.consumer;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import com.teoware.refapp.service.AuthorServiceLocal;
import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.FindAuthorRequest;
import com.teoware.refapp.service.dto.FindAuthorResponse;
import com.teoware.refapp.service.dto.ListAuthorsResponse;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.dto.RegisterAuthorResponse;
import com.teoware.refapp.service.validation.ValidationException;

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AuthorServiceConsumerBean implements AuthorServiceConsumer {

	@EJB
	private AuthorServiceLocal authorService;

	@Override
	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest request) throws ValidationException,
			ServiceException {
		return authorService.registerAuthor(request);
	}

	@Override
	public FindAuthorResponse findAuthor(FindAuthorRequest request) {
		return authorService.findAuthor(request);
	}

	@Override
	public ListAuthorsResponse listAuthors() {
		return authorService.listAuthors();
	}
}
