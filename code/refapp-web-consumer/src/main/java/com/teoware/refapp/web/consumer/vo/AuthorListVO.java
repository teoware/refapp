package com.teoware.refapp.web.consumer.vo;

import java.util.List;

import com.teoware.refapp.model.author.Author;

public class AuthorListVO {

	private List<Author> authorList;

	public AuthorListVO(List<Author> authorList) {
		this.authorList = authorList;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}
}
