package com.teoware.refapp.web;

import javax.ejb.EJB;

import com.teoware.refapp.service.AuthorService;

public class ServiceFacade {

	private static ServiceFacade instance;
	
	@EJB(mappedName="AuthorService")
	private AuthorService authorService;

	public static ServiceFacade getInstance() {
		if (instance == null) {
			instance = new ServiceFacade();
		}
		return instance;
	}

	public AuthorService getAuthorService() {
		return authorService;
	}
}
