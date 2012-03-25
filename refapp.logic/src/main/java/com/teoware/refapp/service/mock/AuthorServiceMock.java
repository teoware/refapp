package com.teoware.refapp.service.mock;

import com.teoware.refapp.dto.AuthorCreateRequest;
import com.teoware.refapp.dto.AuthorCreateResponse;
import com.teoware.refapp.service.AuthorService;

public class AuthorServiceMock implements AuthorService {

	private static final long serialVersionUID = 1L;

	@Override
	public AuthorCreateResponse createAuthor(AuthorCreateRequest authorCreateRequest) {
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
