package com.teoware.refapp.web.ui.controller;

import static com.teoware.refapp.web.ui.util.Globalization.spacer;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.inject.Inject;

import com.teoware.refapp.web.ui.util.Globalization;

public abstract class AbstractControllerBean {

	protected String debug;

	@Inject
	protected Globalization globalization;

	public String getTitle() {
		return dict("app.title");
	}

	public abstract String getPageTitle();

	public String getFullTitle() {
		return getTitle() + spacer + getPageTitle();
	}

	public String getPageFooter() {
		return dict("page.common.footer");
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

	public String dict(String key) {
		return globalization.dict(key);
	}
}
