package com.teoware.refapp.dao.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.dao.AuthorDaoLocal;
import com.teoware.refapp.dao.message.InsertAuthorRequest;
import com.teoware.refapp.dao.message.InsertAuthorResponse;

@Stateless(mappedName = "AuthorDao")
public class AuthorDaoImpl implements AuthorDaoLocal {

	private static final long serialVersionUID = 1L;

	@Override
	public InsertAuthorResponse insertAuthor(InsertAuthorRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
