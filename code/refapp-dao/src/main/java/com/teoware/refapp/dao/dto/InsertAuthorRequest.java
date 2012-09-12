package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;

public class InsertAuthorRequest {

	private Author author;
	private AuthorPassword authorPassword;

	public InsertAuthorRequest() {
	}

	public InsertAuthorRequest(Author author) {
		this.author = author;
	}

	public InsertAuthorRequest(Author author, AuthorPassword authorPassword) {
		this.author = author;
		this.authorPassword = authorPassword;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public AuthorPassword getAuthorPassword() {
		return authorPassword;
	}

	public void setAuthorPassword(AuthorPassword authorPassword) {
		this.authorPassword = authorPassword;
	}
}
