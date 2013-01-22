package com.teoware.refapp.model.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.teoware.refapp.model.base.BaseBody;

public class User extends BaseBody {

	@NotNull
	@Valid
	protected Username username;
	
	@NotNull
	@Valid
	protected UserDetails userDetails;
	
	@NotNull
	@Valid
	protected UserAddress userAddress;
	
	protected UserStatus userStatus;

	public Username getUsername() {
		return username;
	}

	public void setUsername(Username username) {
		this.username = username;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
}
