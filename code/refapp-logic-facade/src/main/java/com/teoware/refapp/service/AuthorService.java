package com.teoware.refapp.service;

import java.io.Serializable;

import com.teoware.refapp.service.dto.FindAuthorRequest;
import com.teoware.refapp.service.dto.FindAuthorResponse;
import com.teoware.refapp.service.dto.ListAuthorsResponse;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.dto.RegisterAuthorResponse;
import com.teoware.refapp.service.validation.ValidationException;

public interface AuthorService extends Serializable {

	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest request) throws ValidationException, ServiceException;
	
	public FindAuthorResponse findAuthor(FindAuthorRequest request) throws ValidationException, ServiceException;
	
	public ListAuthorsResponse listAuthors() throws ServiceException;
}
