package com.teoware.refapp.dao;

import com.teoware.refapp.dto.AuthorCreateRequest;
import com.teoware.refapp.dto.AuthorCreateResponse;

public interface AuthorDao {

	public AuthorCreateResponse createAuthor(AuthorCreateRequest authorCreateRequest);
}
