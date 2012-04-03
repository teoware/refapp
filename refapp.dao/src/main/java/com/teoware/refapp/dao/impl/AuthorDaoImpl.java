package com.teoware.refapp.dao.impl;

import javax.ejb.Stateless;

import com.teoware.refapp.dao.AuthorDaoLocal;
import com.teoware.refapp.dto.AuthorCreateRequest;
import com.teoware.refapp.dto.AuthorCreateResponse;

@Stateless(mappedName = "AuthorDao")
public class AuthorDaoImpl implements AuthorDaoLocal {

	private static final long serialVersionUID = 1L;

	@Override
	public AuthorCreateResponse createAuthor(AuthorCreateRequest authorCreateRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
