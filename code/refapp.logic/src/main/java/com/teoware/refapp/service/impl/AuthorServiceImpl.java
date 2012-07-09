package com.teoware.refapp.service.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.service.AuthorServiceLocal;
import com.teoware.refapp.service.AuthorServiceRemote;
import com.teoware.refapp.service.message.RegisterAuthorRequest;
import com.teoware.refapp.service.message.RegisterAuthorResponse;

@Stateless(mappedName="AuthorService")
public class AuthorServiceImpl implements AuthorServiceLocal, AuthorServiceRemote {

	private static final long serialVersionUID = 1L;

	@Override
	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest registerAuthorRequest) {
		return null;
	}

	@Override
	public String getName() {
		return "Thomas";
	}
}
