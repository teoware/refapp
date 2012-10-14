package com.teoware.refapp.web.consumer;

import com.teoware.refapp.service.ServiceException;
import com.teoware.refapp.service.dto.FindAuthorRequest;
import com.teoware.refapp.service.dto.FindAuthorResponse;
import com.teoware.refapp.service.dto.ListAuthorsResponse;
import com.teoware.refapp.service.dto.RegisterAuthorRequest;
import com.teoware.refapp.service.dto.RegisterAuthorResponse;
import com.teoware.refapp.service.validation.ValidationException;

public interface AuthorServiceConsumer {

	public RegisterAuthorResponse registerAuthor(RegisterAuthorRequest request) throws ValidationException,
			ServiceException;

	public FindAuthorResponse findAuthor(FindAuthorRequest request);

	public ListAuthorsResponse listAuthors();
}
