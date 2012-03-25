package com.teoware.refapp.web.presentation;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.teoware.refapp.service.AuthorService;

@ManagedBean
@SessionScoped
public class IndexPageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB(mappedName="AuthorService")
	private AuthorService authorService;
	
	private String name;

	public String getName() {
		return authorService.getName();
	}

	public void setName(String name) {
		this.name = name;
	}
}
