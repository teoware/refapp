package com.teoware.refapp.model.author;

import com.teoware.refapp.model.base.BaseBody;

public class Author extends BaseBody {

	private AuthorId authorId;
	private AuthorInfo authorInfo;
	private AuthorAddress authorAddress;

	public Author(AuthorId authorId, AuthorInfo authorInfo, AuthorAddress authorAddress) {
		this.authorId = authorId;
		this.authorInfo = authorInfo;
		this.authorAddress = authorAddress;
	}

	public AuthorId getAuthorId() {
		return authorId;
	}

	public AuthorInfo getAuthorInfo() {
		return authorInfo;
	}

	public AuthorAddress getAuthorAddress() {
		return authorAddress;
	}
}
