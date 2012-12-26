package com.teoware.refapp.web.ui.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

public abstract class AbstractControllerBean {

	public static final String DEFAULT_TITLE = "RefApp";
	public static final String TITLE_SPACER = " - ";

	protected String debug;

	public String getTitle() {
		return DEFAULT_TITLE;
	}

	public String getTitle(String subTitle) {
		return DEFAULT_TITLE + TITLE_SPACER + subTitle;
	}

	protected String getStackTrace(Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		e.printStackTrace(printWriter);
		return stringWriter.toString();
	}

	public String getDebug() {
		return debug;
	}

	public void setDebug(String debug) {
		this.debug = debug;
	}
}
