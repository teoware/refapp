package com.teoware.refapp.web.ui;

public abstract class AbstractPageBean {

	protected static final String DEFAULT_TITLE = "RefApp";
	protected static final String TITLE_SPACER = " - ";

	public String getTitle() {
		return DEFAULT_TITLE + TITLE_SPACER;
	}
}
