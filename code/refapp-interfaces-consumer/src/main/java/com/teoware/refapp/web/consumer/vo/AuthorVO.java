package com.teoware.refapp.web.consumer.vo;

import com.teoware.refapp.model.author.Author;

public class AuthorVO {

	Author author;

	public AuthorVO(Author author) {
		this.author = author;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
