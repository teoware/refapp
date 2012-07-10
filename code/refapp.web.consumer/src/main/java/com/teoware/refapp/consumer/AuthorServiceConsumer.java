package com.teoware.refapp.consumer;

import com.teoware.refapp.service.message.FindAuthorRequest;
import com.teoware.refapp.service.message.FindAuthorResponse;
import com.teoware.refapp.service.message.ListAuthorsResponse;
import com.teoware.refapp.service.message.RegisterAuthorRequest;
import com.teoware.refapp.service.message.RegisterAuthorResponse;

public interface AuthorServiceConsumer {

	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest request);
	
	public FindAuthorResponse findAuthor(FindAuthorRequest request);
	
	public ListAuthorsResponse listAuthors();
}
