package com.teoware.refapp.model.author;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.teoware.refapp.model.base.BaseBody;

public class Author extends BaseBody {

	@NotNull
	@Valid
	protected AuthorId authorId;
	@NotNull
	protected AuthorInfo authorInfo;
	@NotNull
	protected AuthorAddress authorAddress;

	public AuthorId getAuthorId() {
		return authorId;
	}

	public void setAuthorId(AuthorId authorId) {
		this.authorId = authorId;
	}

	public AuthorInfo getAuthorInfo() {
		return authorInfo;
	}

	public void setAuthorInfo(AuthorInfo authorInfo) {
		this.authorInfo = authorInfo;
	}

	public AuthorAddress getAuthorAddress() {
		return authorAddress;
	}

	public void setAuthorAddress(AuthorAddress authorAddress) {
		this.authorAddress = authorAddress;
	}
}
