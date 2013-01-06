package com.teoware.refapp.model.util;

import com.teoware.refapp.model.user.User;
import com.teoware.refapp.model.user.UserAddress;
import com.teoware.refapp.model.user.UserInfo;
import com.teoware.refapp.model.user.UserPassword;
import com.teoware.refapp.model.user.UserStatus;
import com.teoware.refapp.model.user.Username;

public class BeanFactory {

	public static Username createUsernameBean() {
		Username username = new Username();
		return username;
	}

	public static UserInfo createUserInfoBean() {
		UserInfo userInfo = new UserInfo();
		return userInfo;
	}

	public static UserAddress createUserAddressBean() {
		UserAddress userAddress = new UserAddress();
		return userAddress;
	}

	public static UserStatus createUserStatusBean() {
		UserStatus userStatus = new UserStatus();
		return userStatus;
	}

	public static User createUserBean() {
		User user = new User();
		user.setUsername(createUsernameBean());
		user.setUserInfo(createUserInfoBean());
		user.setUserAddress(createUserAddressBean());
		user.setUserStatus(createUserStatusBean());
		return user;
	}

	public static UserPassword createUserPasswordBean() {
		return new UserPassword();
	}
}
