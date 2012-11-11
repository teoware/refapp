package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.author.AuthorPassword;
import com.teoware.refapp.model.base.BaseMessage;

public class RegisterAuthorRequest extends BaseMessage<Header, Author> {

	private AuthorPassword authorPassword;

	public RegisterAuthorRequest(Header header, Author author, AuthorPassword authorPassword) {
		super(header, author);
		this.authorPassword = authorPassword;
	}

	public RegisterAuthorRequest(Author author, AuthorPassword authorPassword) {
		this(Header.getInstance(), author, authorPassword);
	}

	public AuthorPassword getAuthorPassword() {
		return authorPassword;
	}
}
