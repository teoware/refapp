package com.teoware.refapp.dao.dto;

import java.util.List;

import com.teoware.refapp.model.user.UserPassword;

public class SelectUserPasswordResponse {

	List<UserPassword> userPasswordList;

	public SelectUserPasswordResponse(List<UserPassword> userPasswordList) {
		this.userPasswordList = userPasswordList;
	}

	public List<UserPassword> getUserPasswordList() {
		return userPasswordList;
	}
}
