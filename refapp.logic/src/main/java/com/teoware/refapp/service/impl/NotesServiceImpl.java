package com.teoware.refapp.service.impl;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.teoware.refapp.service.NotesService;

@Stateless(mappedName = "/refapp/")
@Remote(value = NotesService.class)
public class NotesServiceImpl implements NotesService {

}
