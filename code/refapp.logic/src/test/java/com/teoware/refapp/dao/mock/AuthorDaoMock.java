package com.teoware.refapp.dao.mock;

import com.teoware.refapp.dao.AuthorDao;
import com.teoware.refapp.dao.message.InsertAuthorRequest;
import com.teoware.refapp.dao.message.InsertAuthorResponse;
import com.teoware.refapp.dao.message.SelectAuthorRequest;
import com.teoware.refapp.dao.message.SelectAuthorResponse;
import com.teoware.refapp.dao.message.UpdateAuthorRequest;
import com.teoware.refapp.dao.message.UpdateAuthorResponse;

public class AuthorDaoMock implements AuthorDao {

	private static final long serialVersionUID = 1L;

	@Override
	public InsertAuthorResponse insertAuthor(InsertAuthorRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UpdateAuthorResponse updateAuthor(UpdateAuthorRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SelectAuthorResponse selectAuthor(SelectAuthorRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SelectAuthorResponse selectAuthor() {
		// TODO Auto-generated method stub
		return null;
	}
}
