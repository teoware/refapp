package com.teoware.refapp.dao.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.teoware.refapp.dao.AuthorDao;
import com.teoware.refapp.dto.AuthorCreateRequest;
import com.teoware.refapp.dto.AuthorCreateResponse;

@Stateless(mappedName = "/ejb/")
@Remote(value = AuthorDao.class)
public class AuthorDaoImpl implements AuthorDao {

	private static final long serialVersionUID = 1L;

	@Override
	public AuthorCreateResponse createAuthor(AuthorCreateRequest authorCreateRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
