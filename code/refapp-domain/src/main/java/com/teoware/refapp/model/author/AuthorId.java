package com.teoware.refapp.model.author;

import java.util.Calendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.teoware.refapp.model.enums.AuthorStatus;
import com.teoware.refapp.model.util.ValidationRegex;

public class AuthorId {

	@NotNull
	@Size(min=3, max=20)
	@Pattern(regexp = ValidationRegex.USERNAME)
	protected String userName;
	protected AuthorStatus status;
	protected Calendar created;
	protected Calendar modified;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public AuthorStatus getStatus() {
		return status;
	}

	public void setStatus(AuthorStatus status) {
		this.status = status;
	}

	public Calendar getCreated() {
		return created;
	}

	public void setCreated(Calendar created) {
		this.created = created;
	}

	public Calendar getModified() {
		return modified;
	}

	public void setModified(Calendar modified) {
		this.modified = modified;
	}
}
