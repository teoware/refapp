package com.teoware.refapp.model.common;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.teoware.refapp.model.base.BaseBody;
import com.teoware.refapp.model.util.ValidationRegex;

public class Title extends BaseBody {

	@NotNull
	@Size(min = 1, max = 100)
	@Pattern(regexp = ValidationRegex.TITLE)
	private String title;

	public Title() {
	}

	public Title(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
