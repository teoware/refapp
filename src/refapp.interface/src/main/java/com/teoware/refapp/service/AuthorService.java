package com.teoware.refapp.service;

import java.io.Serializable;

import com.teoware.refapp.dto.AuthorCreateRequest;
import com.teoware.refapp.dto.AuthorCreateResponse;

public interface AuthorService extends Serializable {

	public AuthorCreateResponse createAuthor(AuthorCreateRequest authorCreateRequest);
	
	public String getName();
}
