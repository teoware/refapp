package com.teoware.refapp.model.author;

import com.teoware.refapp.model.base.BaseBody;

public class Author extends BaseBody {

	protected AuthorId authorId;
	protected AuthorInfo authorInfo;
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
