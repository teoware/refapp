package com.teoware.refapp.service.mock;

import com.teoware.refapp.dao.AuthorDaoLocal;
import com.teoware.refapp.service.AuthorServiceBean;

public class AuthorServiceMock extends AuthorServiceBean {

	private static final long serialVersionUID = 1L;

	public void setAuthorDao(AuthorDaoLocal authorDaoMock) {
		this.authorDao = authorDaoMock;
	}
}
