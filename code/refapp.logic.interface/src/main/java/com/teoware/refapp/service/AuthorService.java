package com.teoware.refapp.service;

import java.io.Serializable;

import com.teoware.refapp.service.message.FindAuthorRequest;
import com.teoware.refapp.service.message.FindAuthorResponse;
import com.teoware.refapp.service.message.ListAuthorsResponse;
import com.teoware.refapp.service.message.RegisterAuthorRequest;
import com.teoware.refapp.service.message.RegisterAuthorResponse;

public interface AuthorService extends Serializable {

	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest registerAuthorRequest);
	
	public FindAuthorResponse findAuthor(FindAuthorRequest request);
	
	public ListAuthorsResponse listAuthors();
	
	public String getName();
}
