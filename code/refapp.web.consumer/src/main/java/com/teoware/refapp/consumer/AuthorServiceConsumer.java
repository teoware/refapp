package com.teoware.refapp.consumer;

import com.teoware.refapp.service.dto.FindAuthorRequest;
import com.teoware.refapp.service.dto.FindAuthorResponse;
import com.teoware.refapp.service.dto.ListAuthorsResponse;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.dto.RegisterAuthorResponse;

public interface AuthorServiceConsumer {

	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest request);
	
	public FindAuthorResponse findAuthor(FindAuthorRequest request);
	
	public ListAuthorsResponse listAuthors();
}
