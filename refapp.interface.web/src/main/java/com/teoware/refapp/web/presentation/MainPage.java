package com.teoware.refapp.web.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class MainPage {

	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getHello() {
		return "Hello " + name;
	}
}
