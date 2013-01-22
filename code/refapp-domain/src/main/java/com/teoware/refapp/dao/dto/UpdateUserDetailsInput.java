package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.UserDetails;

public class UpdateUserDetailsInput {

	private Id userId;
	private UserDetails userDetails;

	public UpdateUserDetailsInput() {
	}

	public UpdateUserDetailsInput(Id userId, UserDetails userDetails) {
		this.userId = userId;
		this.userDetails = userDetails;
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
}
