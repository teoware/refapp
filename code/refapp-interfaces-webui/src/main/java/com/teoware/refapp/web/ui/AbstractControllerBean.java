package com.teoware.refapp.web.ui;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractControllerBean {

	protected static final String DEFAULT_TITLE = "RefApp";
	protected static final String TITLE_SPACER = " - ";

	public String getTitle() {
		return DEFAULT_TITLE + TITLE_SPACER;
	}

	protected String getStackTrace(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}
}
