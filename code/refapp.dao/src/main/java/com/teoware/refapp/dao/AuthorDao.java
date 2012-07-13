package com.teoware.refapp.dao;

import java.io.Serializable;

import com.teoware.refapp.dao.message.InsertAuthorRequest;
import com.teoware.refapp.dao.message.InsertAuthorResponse;
import com.teoware.refapp.dao.message.SelectAuthorRequest;
import com.teoware.refapp.dao.message.SelectAuthorResponse;
import com.teoware.refapp.dao.message.UpdateAuthorRequest;
import com.teoware.refapp.dao.message.UpdateAuthorResponse;

public interface AuthorDao extends Serializable {

	public InsertAuthorResponse insertAuthor(InsertAuthorRequest request) throws DaoException;
	
	public UpdateAuthorResponse updateAuthor(UpdateAuthorRequest request);
	
	public SelectAuthorResponse selectAuthor(SelectAuthorRequest request);
	
	public SelectAuthorResponse selectAuthor();
}
