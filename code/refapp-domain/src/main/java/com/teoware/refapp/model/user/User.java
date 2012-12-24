package com.teoware.refapp.model.user;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.teoware.refapp.model.base.BaseBody;

public class User extends BaseBody {

	@NotNull
	@Valid
	protected UserId userId;
	@NotNull
	protected UserInfo userInfo;
	@NotNull
	protected UserAddress userAddress;

	public UserId getUserId() {
		return userId;
	}

	public void setUserId(UserId userId) {
		this.userId = userId;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public UserAddress getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(UserAddress userAddress) {
		this.userAddress = userAddress;
	}
}
