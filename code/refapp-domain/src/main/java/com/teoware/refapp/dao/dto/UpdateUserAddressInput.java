package com.teoware.refapp.dao.dto;

import com.teoware.refapp.model.user.UserAddress;

public class UpdateUserAddressInput {

	private Id userId;
	private UserAddress userAddress;

	public UpdateUserAddressInput() {
	}

	public UpdateUserAddressInput(Id userId, UserAddress userAddress) {
		this.userId = userId;
		this.userAddress = userAddress;
	}

	public Id getUserId() {
		return userId;
	}

	public void setUserId(Id userId) {
		this.userId = userId;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
}
