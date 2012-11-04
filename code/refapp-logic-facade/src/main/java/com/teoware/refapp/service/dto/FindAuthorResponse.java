package com.teoware.refapp.service.dto;

import com.teoware.refapp.model.Header;
import com.teoware.refapp.model.author.Author;
import com.teoware.refapp.model.base.BaseMessage;

public class FindAuthorResponse extends BaseMessage<Header, Author> {

	public FindAuthorResponse(Header header, Author author) {
		super(header, author);
	}
}
