package com.teoware.refapp.service.dto;

import java.util.List;

import com.teoware.refapp.model.author.Author;

public class ListAuthorsResponse {

	private List<Author> authorList;

	public ListAuthorsResponse(List<Author> authorList) {
		this.authorList = authorList;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}
}
