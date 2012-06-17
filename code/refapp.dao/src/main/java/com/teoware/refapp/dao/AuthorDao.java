package com.teoware.refapp.dao;

import java.io.Serializable;

import com.teoware.refapp.dto.AuthorCreateRequest;
import com.teoware.refapp.dto.AuthorCreateResponse;

public interface AuthorDao extends Serializable {

	public AuthorCreateResponse createAuthor(AuthorCreateRequest authorCreateRequest);
}
