package com.teoware.refapp.dao;

import java.io.Serializable;

import com.teoware.refapp.dao.message.InsertAuthorRequest;
import com.teoware.refapp.dao.message.InsertAuthorResponse;

public interface AuthorDao extends Serializable {

	public InsertAuthorResponse insertAuthor(InsertAuthorRequest request);
}
