package com.teoware.refapp.consumer.impl;

import javax.ejb.EJB;

import com.teoware.refapp.consumer.AuthorServiceConsumer;
import com.teoware.refapp.service.AuthorService;
import com.teoware.refapp.service.message.FindAuthorRequest;
import com.teoware.refapp.service.message.FindAuthorResponse;
import com.teoware.refapp.service.message.ListAuthorsResponse;
import com.teoware.refapp.service.message.RegisterAuthorRequest;
import com.teoware.refapp.service.message.RegisterAuthorResponse;

public class AuthorServiceConsumerImpl implements AuthorServiceConsumer {

	private static AuthorServiceConsumer instance;
	
	@EJB
	private AuthorService authorService;
	
	public static AuthorServiceConsumer getInstance() {
		if (instance == null) {
			instance = new AuthorServiceConsumerImpl();
		}
		return instance;
	}

	@Override
	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest request) {
		return authorService.registerAuthor(request);
	}

	@Override
	public FindAuthorResponse findAuthor(FindAuthorRequest request) {
		return authorService.findAuthor(request);
	}

	@Override
	public ListAuthorsResponse listAuthors() {
		return null;//authorService.listAuthors();
	}
}
