package com.teoware.refapp.dao.dto;

import java.util.List;

import com.teoware.refapp.model.author.AuthorPassword;

public class SelectAuthorPasswordResponse {

	List<AuthorPassword> authorPasswordList;

	public SelectAuthorPasswordResponse(List<AuthorPassword> authorPasswordList) {
		this.authorPasswordList = authorPasswordList;
	}

	public List<AuthorPassword> getAuthorPasswordList() {
		return authorPasswordList;
	}
}
