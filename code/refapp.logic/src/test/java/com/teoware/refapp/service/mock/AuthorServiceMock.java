package com.teoware.refapp.service.mock;

import com.teoware.refapp.dao.AuthorDaoLocal;
import com.teoware.refapp.service.impl.AuthorServiceImpl;

public class AuthorServiceMock extends AuthorServiceImpl {

	private static final long serialVersionUID = 1L;

	public void setAuthorDao(AuthorDaoLocal authorDaoMock) {
		this.authorDao = authorDaoMock;
	}
}
