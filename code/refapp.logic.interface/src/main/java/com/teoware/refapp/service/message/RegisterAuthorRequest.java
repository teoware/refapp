package com.teoware.refapp.service.message;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.base.BaseMessage;

public class RegisterAuthorRequest extends BaseMessage<Header, Author> {
	
	public RegisterAuthorRequest(Header header, Author author) {
		super(header, author);
	}

	public RegisterAuthorRequest(Author author) {
		this(Header.getInstance(), author);
	}
}
