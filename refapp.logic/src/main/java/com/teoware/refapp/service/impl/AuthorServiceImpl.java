package com.teoware.refapp.service.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.teoware.refapp.dto.AuthorCreateRequest;
import com.teoware.refapp.dto.AuthorCreateResponse;
import com.teoware.refapp.service.AuthorService;

@Stateless(mappedName = "AuthorService")
@Remote(value = AuthorService.class)
public class AuthorServiceImpl implements AuthorService {

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
