package com.teoware.refapp.dao.dto;

import java.util.List;

import com.teoware.refapp.model.author.Author;

public class SelectAuthorResponse {

	List<Author> authorList;

	public SelectAuthorResponse(List<Author> authorList) {
		this.authorList = authorList;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}
}
