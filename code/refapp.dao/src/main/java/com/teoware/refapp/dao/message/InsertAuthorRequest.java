package com.teoware.refapp.dao.message;

import com.teoware.refapp.model.author.Author;

public class InsertAuthorRequest {

	private Author author;

	public InsertAuthorRequest() {
	}

	public InsertAuthorRequest(Author author) {
		this.author = author;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
