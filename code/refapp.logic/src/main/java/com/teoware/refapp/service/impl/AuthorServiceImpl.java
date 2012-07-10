package com.teoware.refapp.service.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.teoware.refapp.dao.AuthorDaoLocal;
import com.teoware.refapp.service.AuthorServiceLocal;
import com.teoware.refapp.service.AuthorServiceRemote;
import com.teoware.refapp.service.message.FindAuthorRequest;
import com.teoware.refapp.service.message.FindAuthorResponse;
import com.teoware.refapp.service.message.ListAuthorsResponse;
import com.teoware.refapp.service.message.RegisterAuthorRequest;
import com.teoware.refapp.service.message.RegisterAuthorResponse;

@Stateless(mappedName="AuthorService")
public class AuthorServiceImpl implements AuthorServiceLocal, AuthorServiceRemote {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	protected AuthorDaoLocal authorDao;

	@Override
	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest registerAuthorRequest) {
		return null;
	}

	@Override
	public String getName() {
		return "Thomas";
	}

	@Override
	public FindAuthorResponse findAuthor(FindAuthorRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListAuthorsResponse listAuthors() {
		// TODO Auto-generated method stub
		return null;
	}
}
