package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;

public class RegisterAuthorRequestVO {

	private Author author;
	private AuthorPassword authorPassword;

	public RegisterAuthorRequestVO(Author author, AuthorPassword authorPassword) {
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
