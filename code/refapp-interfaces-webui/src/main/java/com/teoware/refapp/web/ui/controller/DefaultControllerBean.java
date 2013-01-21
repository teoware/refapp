package com.teoware.refapp.web.ui.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class DefaultControllerBean extends AbstractControllerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public String getPageTitle() {
		return "";
	}

	@Override
	public String getFullTitle() {
		return getTitle();
	}
}
