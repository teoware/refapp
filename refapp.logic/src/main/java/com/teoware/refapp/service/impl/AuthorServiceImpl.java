package com.teoware.refapp.service.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.teoware.refapp.service.AuthorService;

@Stateless(mappedName = "/refapp/")
@Remote(value = AuthorService.class)
public class AuthorServiceImpl implements AuthorService {

}
