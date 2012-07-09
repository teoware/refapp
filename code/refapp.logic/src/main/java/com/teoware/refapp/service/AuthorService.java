package com.teoware.refapp.service;

import java.io.Serializable;

import com.teoware.refapp.service.message.RegisterAuthorRequest;
import com.teoware.refapp.service.message.RegisterAuthorResponse;

public interface AuthorService extends Serializable {

	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest registerAuthorRequest);
	
	public String getName();
}
