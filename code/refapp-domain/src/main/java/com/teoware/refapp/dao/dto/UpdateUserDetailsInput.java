package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.UserDetails;

public class UpdateUserDetailsInput extends ChangeInput {

	private UserDetails userDetails;

	public UpdateUserDetailsInput() {
		super();
	}

	public UpdateUserDetailsInput(Id id, UserDetails userDetails) {
		super(id);
		this.userDetails = userDetails;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
}
