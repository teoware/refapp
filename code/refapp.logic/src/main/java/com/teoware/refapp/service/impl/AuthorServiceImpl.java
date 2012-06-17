package com.teoware.refapp.service.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.dto.AuthorCreateRequest;
import com.teoware.refapp.dto.AuthorCreateResponse;
import com.teoware.refapp.service.AuthorServiceLocal;
import com.teoware.refapp.service.AuthorServiceRemote;

@Stateless(name="AuthorService", mappedName="/ejb/")
public class AuthorServiceImpl implements AuthorServiceLocal, AuthorServiceRemote {

	private static final long serialVersionUID = 1L;

	@Override
	public AuthorCreateResponse createAuthor(AuthorCreateRequest authorCreateRequest) {
		return null;
	}

	@Override
	public String getName() {
		return "Thomas";
	}
}
