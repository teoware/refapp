package com.teoware.refapp.dao.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.teoware.refapp.dao.AuthorDao;
import com.teoware.refapp.dto.AuthorCreateRequest;
import com.teoware.refapp.dto.AuthorCreateResponse;

@Stateless(mappedName = "/refapp/")
@Remote(value = AuthorDao.class)
public class AuthorDaoImpl implements AuthorDao {

	@Override
	public AuthorCreateResponse createAuthor(AuthorCreateRequest authorCreateRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
