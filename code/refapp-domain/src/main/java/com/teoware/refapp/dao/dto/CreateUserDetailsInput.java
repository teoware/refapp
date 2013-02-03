package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.common.Id;
import com.teoware.refapp.model.user.UserDetails;

public class CreateUserDetailsInput extends ChangeInput {

	private UserDetails userDetails;

	public CreateUserDetailsInput() {
	}

	public CreateUserDetailsInput(Id id, UserDetails userDetails) {
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
